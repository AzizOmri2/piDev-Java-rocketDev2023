/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import Entity.Competition;
import Services.CompetitionServices;
import com.sun.javafx.scene.control.skin.ColorPalette;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ColorPicker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


/**
 * FXML Controller class
 *
 * @author Salima
 */
public class StatistiqueController implements Initializable {

    @FXML
    private TabPane tabPanePieChart;
    @FXML
    private Tab tab1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            initPieChart();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
private ChartPanel initPieChart() throws SQLException {
    JFreeChart chart = createChart("Statistique: compétitions selon le nombre des participants");
    ChartPanel chartPanel = new ChartPanel(chart);
    return chartPanel;
}

private static PieDataset createDataset() throws SQLException {
    DefaultPieDataset dataset = new DefaultPieDataset();

    CompetitionServices cs = new CompetitionServices();
    List<Competition> competitions = cs.afficherListe();

    // Créer une Map pour stocker le nombre de participants pour chaque compétition
    Map<String, Integer> participantsByCompetition = new HashMap<>();
    for (Competition c : competitions) {
        participantsByCompetition.put(c.getNomCompetition(), c.getNbrParticipants());
    }

    // Créer une Map pour stocker le nombre d'utilisateurs pour chaque nombre de participants
    Map<Integer, Integer> usersByParticipants = new HashMap<>();
    for (int participants : participantsByCompetition.values()) {
        int count = usersByParticipants.getOrDefault(participants, 0);
        usersByParticipants.put(participants, count + 1);
    }

    // Ajouter les valeurs au dataset
    for (int participants : usersByParticipants.keySet()) {
        dataset.setValue(String.valueOf(participants), usersByParticipants.get(participants));
    }

    return dataset;
}

private static JFreeChart createChart(String name) throws SQLException {
    PieDataset dataset = createDataset();
    JFreeChart chart = ChartFactory.createPieChart(name, dataset, false, true, false);
    PiePlot plot = (PiePlot) chart.getPlot();

    // Utiliser une palette de couleurs pour les sections du graphique
    Random rand = new Random();
    for (int i = 0; i < dataset.getItemCount(); i++) {
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        plot.setSectionPaint(i, new Color(r, g, b));
    }

    plot.setLabelFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
    // Custom labels https://stackoverflow.com/a/17507061/230513
    PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {2}", new DecimalFormat("0"), new DecimalFormat("0.0%"));
    plot.setLabelGenerator(gen);
    return chart;
}


}
