/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Activite;
import entities.Cours;
import entities.Planning;
import entities.Reclamation;

/**
 *
 * @author azizo
 */
public interface MyListener {
    public void onClickListener(Activite act);
    public void onClickListener(Cours cours);
    public void onClickListener(Planning p);
    public void onClickListener(Reclamation rec);
    
}
