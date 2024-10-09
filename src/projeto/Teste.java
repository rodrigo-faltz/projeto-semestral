package projeto;

import javax.swing.*;

public class Teste {
        public static void main(String[] args) {
            
            SwingUtilities.invokeLater(new Runnable(){
                public void run() {
                    System.setProperty("sun.java2d.uiScale", "1.0");
                    new TelaInicial();
                }
            });
        }
    }