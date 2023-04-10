/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Abonnement;
import Entities.AbonnementCRUD;
import Entities.Pack;
import Entities.Promotion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 *
 * @author wiem
 */
public class AbonnementService implements AbonnementCRUD<Abonnement> {

    public Connection conx;
    public Statement stm;

    public AbonnementService() {
        conx = MyDB.getInstance().getConx();
    }

    public boolean isValid(String codePromotion) throws SQLException {
        PromotionService promotionservice = new PromotionService();
        Promotion promotion = promotionservice.findByCodePromotion(codePromotion);

        if (promotion == null || !promotion.getCodePromotion().equals(codePromotion)) {
            return false;
        }

        return true;
    }

    public Abonnement findOneByUser(int userId) throws SQLException {
        String sql = "SELECT * FROM abonnement WHERE user_id = ?";
        try (PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Abonnement abonnement = new Abonnement();
                abonnement.setId(rs.getInt("id"));
                return abonnement;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void create(Abonnement abonnement) throws SQLException {
        LocalDate dateT = LocalDate.now();
        PackService p = new PackService();
        PromotionService promotionservice = new PromotionService();
        String sql = "INSERT INTO abonnement (date_achat, date_fin, etat_abonnement, code_promo, montant_abonnement,pack_id ,user_id)  VALUES (?,?, ? , ? , ? ,? ,? )";
        String s = "UPDATE pack SET places_pack = ? where id = ? ";
        PreparedStatement ps = conx.prepareStatement(s);
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            //test user 
            int userId = abonnement.getUserId();
            Abonnement abonnementExistant = this.findOneByUser(userId);
            if (abonnementExistant != null) {
                String etat = abonnementExistant.getEtatAbonnement();
                if (etat == "actif") {
                    System.out.println("l utilisateur a déjà un abonnement en cours. !");
                }
            } else {
                int packId = abonnement.getPackId();
                List<Pack> packs = p.getAll();
                for (int i = 0; i < packs.size(); i++) {
                    if (packId == packs.get(i).getId()) {
                        Pack pack = packs.get(i);
                        int duree = pack.getDureePack();
                        int disponibilite = pack.getDisponibilitePack();
                        int places = pack.getPlacesPack();
                        System.err.println(places);
                        if (disponibilite == places) {
                            System.out.println("Désolé, les places sont épuisées !");
                        } else {
                            //ne9sa
                            System.err.println(places);
                            int packAjout = places + 1;
                            System.err.println(places + " " + packAjout);
                            pack.setPlacesPack(packAjout);
                            System.err.println(pack.getPlacesPack());
                            //date achat
                            pstmt.setDate(1, Date.valueOf(dateT));
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(Date.valueOf(dateT));
                            calendar.add(Calendar.MONTH, duree);
                            java.sql.Date sqlDate = new java.sql.Date(calendar.getTime().getTime());
                            //date fin
                            pstmt.setDate(2, sqlDate);
                            //etat abonnement 
                            java.sql.Date dateFin = (java.sql.Date) abonnement.getDateFin();
                            LocalDate localDate = dateFin.toLocalDate();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            String formatFin = localDate.format(formatter);
                            LocalDate ff = LocalDate.now();
                            LocalDate dtt = LocalDate.parse(formatFin, formatter);

                            if (ff.compareTo(dtt) <= 0) {
                                pstmt.setString(3, "non actif");
                            } else {
                                pstmt.setString(3, "actif");
                            }

                            pstmt.setString(4, abonnement.getCodePromo());
                            //montant abonnement
                            String codepromo = abonnement.getCodePromo();
                            if (codepromo != null) {
                                Promotion promotion = promotionservice.findByCodePromotion(codepromo);
                                if (promotion != null && isValid(codepromo)) {
                                    double promo = promotion.getReductionPromotion();
                                    double m = pack.getMontantPack();
                                    double promotion1 = m * promo;
                                    double newMontant = m - promotion1;
                                    pstmt.setDouble(5, (Double) newMontant);
                                }
                            } else {
                                double newMontant = pack.getMontantPack();
                                pstmt.setDouble(5, (Double) newMontant);
                            }
                       
                            pstmt.setInt(6, abonnement.getPackId());
                            pstmt.setInt(7, abonnement.getUserId());

                            pstmt.executeUpdate();
                            System.out.println("Abonnement ajouté");
                            PreparedStatement updateStmt = conx.prepareStatement(s);
                            updateStmt.setInt(1, packAjout);
                            updateStmt.setInt(2, pack.getId());
                            updateStmt.executeUpdate();
                        }
                    }
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Abonnement getOneById(int id) throws SQLException {
        String sql = "SELECT * FROM abonnement WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Abonnement p = new Abonnement();
                p.setId(rs.getInt("id"));
                p.setDateAchat(rs.getDate("date_achat"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setEtatAbonnement(rs.getString("etat_abonnement"));
                p.setCodePromo(rs.getString("code_promo"));
                p.setMontantAbonnement(rs.getFloat("montant_abonnement"));
                p.setPackId(rs.getInt("pack_id"));
                p.setId(rs.getInt("id"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Abonnement abonnement, int id) throws SQLException {

        String sql = "UPDATE abonnement SET date_achat = ?, date_fin = ? , etat_abonnement = ? ,"
                + " code_promo = ? , montant_abonnement = ?  WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setDate(1, (Date) abonnement.getDateAchat());
            pstmt.setDate(2, (Date) abonnement.getDateFin());
            pstmt.setString(3, abonnement.getEtatAbonnement());
            pstmt.setString(4, abonnement.getCodePromo());
            pstmt.setFloat(5, (float) abonnement.getMontantAbonnement());
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            System.out.println("Abonnement updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifier(Abonnement abonnement) {
        try {
            String req = "UPDATE abonnement SET date_achat = ?, date_fin = ? , etat_abonnement = ? ,"
                    + " code_promo = ? , montant_abonnement = ?  WHERE id = ?";
            PreparedStatement pstmt = conx.prepareStatement(req);
            pstmt.setDate(1, new java.sql.Date(abonnement.getDateAchat().getTime()));
            pstmt.setDate(2, new java.sql.Date(abonnement.getDateFin().getTime()));
            pstmt.setString(3, abonnement.getEtatAbonnement());
            pstmt.setString(4, abonnement.getCodePromo());
            pstmt.setFloat(5, (float) abonnement.getMontantAbonnement());
            pstmt.setInt(6, abonnement.getId());
            pstmt.executeUpdate();

            System.out.println("Abonnement " + abonnement.getId() + " Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean updateAbonnement(Abonnement p) {
        try {
            String sql = "UPDATE `abonnement` SET `date_achat` = '" + p.getDateAchat() + "', `date_fin` = '" + p.getDateFin() + "', `etat_abonnement` = '" + p.getEtatAbonnement() + "', `code_promo` = " + p.getCodePromo() + ", `montant_abonnement` = '" + p.getMontantAbonnement() + "' WHERE `abonnement`.`id` = " + p.getId();

            PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.executeUpdate(sql);
            System.out.print("                         *************************************************\n");
            System.out.print("                                ********  Abonnement UPDATED SUCCEFULLY  **********\n");
            System.out.print("                         ************************************************\n\n\n");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public void delete(int id) throws SQLException {
        //   PackService p = new PackService();

        String sql = "DELETE FROM abonnement WHERE id = ?";
        try {
            PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            /*int packId = pstmt.getInt();
           List<Pack> packs = p.getAll();
                for (int i = 0; i < packs.size(); i++) {
                    if (packId == packs.get(i).getId()){}}*/
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Supprimer(Abonnement t) {
        try {
            String req = "DELETE FROM abonnement WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("abonnement suprimée !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM abonnement";
        try {
            PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Abonnement> getAll() throws SQLException {
        String sql = "Select * From abonnement";
        PreparedStatement pstmt = conx.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Abonnement> abonnements = new ArrayList<Abonnement>();
        while (rs.next()) {
            Abonnement p = new Abonnement(rs.getInt("id"),//or rst.getInt(1)
                    rs.getDate("date_achat"),
                    rs.getDate("date_fin"),
                    rs.getString("etat_abonnement"),
                    rs.getString("code_promo"),
                    rs.getFloat("montant_abonnement")
            );
            abonnements.add(p);
        }
        return abonnements;
    }

    /*
    public String newAbonnement(@ModelAttribute("abonnement") Abonnement abonnement, BindingResult result, Model model) {
        LocalDateTime currentDate = LocalDateTime.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        abonnement.setDateAchat(currentDate);

        if (!result.hasErrors()) {
            //user 
            User user = abonnement.getUser();
            Abonnement abonnementExistant = abonnementRepository.findByUser(user);
            if (abonnementExistant != null) {
                String etat = abonnementExistant.getEtatAbonnement();
                if (etat.equals("actif")) {
                    model.addAttribute("alertMessage", "L'utilisateur a déjà un abonnement en cours.");
                    return "abonnement/new";
                }
            }

            Pack pack = abonnement.getPackId();
            int packDuree = pack.getDureePack();
            int packDisponibilite = pack.getDisponibilitePack();
            int packPlaces = pack.getPlacesPack();
            if (packDisponibilite == packPlaces) {
                model.addAttribute("alertMessage", "Désolé, les places sont épuisées !");
                return "abonnement/new";
            }

            if (packDisponibilite > packPlaces) {
                int packAjout = packPlaces + 1;
                pack.setPlacesPack(packAjout);
                float montantPack = pack.getMontantPack();
                abonnement.setMontantAbonnement(montantPack);
                LocalDateTime dateFin = currentDate.plus(packDuree, ChronoUnit.MONTHS);
                abonnement.setDateFin(Date.from(dateFin.atZone(ZoneId.systemDefault()).toInstant()));

                Date dateAchat = abonnement.getDateAchat();
                Date dateFinAbonnement = abonnement.getDateFin();
                long timeDiff = dateFinAbonnement.getTime() - dateAchat.getTime();
                if (timeDiff >= 0) {
                    abonnement.setEtatAbonnement("actif");
                } else {
                    abonnement.setEtatAbonnement("non actif");
                }

                String codePromo = abonnement.getCodePromo();
                if (codePromo != null) {
                    Promotion promotion = promotionRepository.findByCodePromotion(codePromo);
                    if (promotion != null && isValid(codePromo)) {
                        float promo = promotion.getReductionPromotion();
                        float m = pack.getMontantPack();
                        float promotion1 = m * promo;
                        float newMontant = m - promotion1;
                        abonnement.setMontantAbonnement(newMontant);
                    }
                } else {
                    abonnement.setMontantAbonnement(pack.getMontantPack());
                }

                abonnementRepository.save(abonnement);
                em.persist(abonnement);
                em.flush();

                return "redirect:/abonnement/index";
            }
        }

        model.addAttribute("abonnement", abonnement);
        return "abonnement/new";
    }

    private boolean isValid(String codePromo) {
        // TODO: implement validation logic
        return true;
    }*/
}
