
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.awt.*;

public class TelaDeAtaque extends JFrame implements ActionListener, MouseListener
{
	private JButton botoes[][], botao1;
    private JPanel painel1, painel2;
    public Color fundo, fundo2;
    String ultimoString, antString, answString, op ;
    int gira, numeroDeNavios;
    double ant, answ;
    int x[][];
    Imagens imgs;
	public TelaDeAtaque()
    {
		super("Batalha Espacial - Tela de Ataque");

        x = new int[10][10];
        imgs = new Imagens();
        numeroDeNavios = 0;
        Grid gridInstance = Grid.getInstance();
        if(gridInstance.getPlayer() == 1){
            System.arraycopy(gridInstance.getGridDoPlayer2(), 0, x, 0, x.length);
        }
        else if (gridInstance.getPlayer() == 2){
            System.arraycopy(gridInstance.getGridDoPlayer1(), 0, x, 0, x.length);
        }
        
        
        
        

        Container caixa = getContentPane();
        caixa.setLayout(new BorderLayout());
        painel1 = new JPanel(new GridLayout(10,10));
        painel2 = new JPanel(new FlowLayout());
        botoes = new JButton[10][10];
        criaGrid(botoes, painel1, x);
        botao1 = new JButton(imgs.botaoSalvar);
        botao1.setContentAreaFilled(false);
        botao1.setBorderPainted(false);


        botao1.setPreferredSize(new Dimension(170, 50));
        painel2.add(botao1);
        botao1.addActionListener(this);
        botao1.addMouseListener(this);

        caixa.add(painel1, BorderLayout.CENTER);
        caixa.add(painel2, BorderLayout.SOUTH);

        setSize(900,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        painel1.setBackground(imgs.corDoFundo);
        painel2.setBackground(imgs.corDoFundo);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, 0);
    }    

    public void actionPerformed(ActionEvent e)
    {   
        if(e.getSource() == botao1){
            JOptionPane.showMessageDialog(null, "Salvo!");
            new CriaDBs();
            new EnviaProDB();
            System.exit(0);
            
        }
        for (int j = 0; j<10;j++){
            for (int i = 0; i<10;i++){
                if(e.getSource()==botoes[j][i]){
                    Grid gridInstance = Grid.getInstance();
                    if(x[j][i] > 1){
                        botoes[j][i].setDisabledIcon(imgs.acerto);
                        botoes[j][i].setOpaque(false);
                        botoes[j][i].setContentAreaFilled(false);
                        botoes[j][i].setBorderPainted(false);
                        botoes[j][i].setEnabled(false);
                        
                        if(gridInstance.getPlayer() == 1){
                            
                            gridInstance.calculaNumeroDeAcertosPlayer1();
                            verificaQualNaveAcertouP1(botoes ,x, j, i, gridInstance);
                            if(gridInstance.getNumeroDeAcertosPlayer1()==14){
                                dispose();
                                new TelaVitoria();

                            }
                        }
                        else if(gridInstance.getPlayer() == 2){
                            gridInstance.calculaNumeroDeAcertosPlayer2();
                            verificaQualNaveAcertouP2(botoes ,x, j, i, gridInstance);
                            if(gridInstance.getNumeroDeAcertosPlayer2()==14){
                                dispose();
                                new TelaVitoria();

                            }
                        }


                    }
                    else{
                        if(gridInstance.getPlayer() == 1){
                            gridInstance.setGridDoPlayer2(x);
                            gridInstance.setPlayer(2);
                        }
                        else if(gridInstance.getPlayer() == 2){
                            gridInstance.setGridDoPlayer1(x);
                            gridInstance.setPlayer(1);
                        }
                        x[j][i] = -1;
                        dispose();
                        new TelaIntemediaria();
                    }
                }
            }
        }
    }
    public void mouseEntered(MouseEvent e) {
            for (int j = 0; j<10;j++){
                for (int i = 0; i<10;i++){ 
                    if((e.getSource()==botoes[j][i]) && x[j][i] != -1){
                        botoes[j][i].setIcon(imgs.mira);
                    }
                }
            }
    }
    public void mouseExited(MouseEvent e) {
                for (int o = 0; o<10;o++){
                    for (int p = 0; p<10;p++){
                        if((e.getSource()==botoes[o][p]) && x[o][p] != -1){ //Acho q isso daqui da pra simplifica ent vou comentar dps
                            botoes[o][p].setIcon(imgs.fundo);
                        }
                    }
                }
    }
    
    public void mouseReleased(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {

    }

    // ------------------- Funcoes ------------------- //

    public void criaGrid(JButton buttons[][], JPanel panel, int x[][]){
        Grid gridInstance = Grid.getInstance();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j<10;j++){
                buttons[i][j] = new JButton(imgs.fundo);
                buttons[i][j].setBorderPainted(false);
                if(x[i][j] == -2 || x[i][j] == -3 || x[i][j] == -4 || x[i][j] == -5){
                    buttons[i][j].setDisabledIcon(imgs.acerto);
                    buttons[i][j].setOpaque(false);
                    buttons[i][j].setContentAreaFilled(false);
                    buttons[i][j].setBorderPainted(false);
                    buttons[i][j].setEnabled(false);
                    
                }
                else if(x[i][j] == -1){
                    buttons[i][j].setDisabledIcon(imgs.erro);
                    buttons[i][j].setEnabled(false);
                }


                panel.add(buttons[i][j]);
                buttons[i][j].addActionListener(this);
                buttons[i][j].addMouseListener(this);
            }
        }
        for(int i = 0; i < 10; i++){
            for(int j = 0; j<10;j++){
                if(gridInstance.getPlayer() == 1){
                    recolocaNavesDestruidasP1(buttons, x, j, i, gridInstance);
                }
                else{
                    recolocaNavesDestruidasP2(buttons, x, j, i, gridInstance);
                }
            }
        }    
    }

    public void verificaQualNaveAcertouP1(JButton gridDeBotoes[][], int grid[][], int coluna, int linha, Grid gridDosNavios){
        if(grid[coluna][linha] == 2){
            gridDosNavios.calculaAcertosNave2P1();
            x[coluna][linha] = -2;
            if(gridDosNavios.getAcertosNave2P1() == 0){
                //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 2");
                naveDestruidas(gridDeBotoes, grid, 2, imgs.impNave2VerticalD, imgs.impNave2HorizontalD);
                
            }
            
        }
        if(grid[coluna][linha] == 3){
            gridDosNavios.calculaAcertosNave3P1();
            x[coluna][linha] = -3;
            if(gridDosNavios.getAcertosNave3P1() == 0){
                //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 3");
                naveDestruidas(gridDeBotoes, grid, 3, imgs.impNave3VerticalD, imgs.impNave3HorizontalD);
            }
            
        }
        if(grid[coluna][linha] == 4){
            gridDosNavios.calculaAcertosNave4P1();
            x[coluna][linha] = -4;
            if(gridDosNavios.getAcertosNave4P1() == 0){
                //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 4");
                naveDestruidas(gridDeBotoes, grid, 4, imgs.impNave4VerticalD, imgs.impNave4HorizontalD);
            }
            
        }
        if(grid[coluna][linha] == 5){
            gridDosNavios.calculaAcertosNave5P1();
            x[coluna][linha] = -5;
            if(gridDosNavios.getAcertosNave5P1() == 0){
                //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 5");
                naveDestruidas(gridDeBotoes, grid, 5, imgs.impNave5VerticalD, imgs.impNave5HorizontalD);
            }
            
        }
    }

    public void verificaQualNaveAcertouP2(JButton gridDeBotoes[][],int grid[][], int coluna, int linha, Grid gridDosNavios){
        if(grid[coluna][linha] == 2){
            gridDosNavios.calculaAcertosNave2P2();
            x[coluna][linha] = -2;
            if(gridDosNavios.getAcertosNave2P2() == 0){
                //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 2");
                naveDestruidas(gridDeBotoes, grid, 2, imgs.republicaNave2VerticalD, imgs.republicaNave2HorizontalD);
            }
            
        }
        if(grid[coluna][linha] == 3){
            gridDosNavios.calculaAcertosNave3P2();
            x[coluna][linha] = -3;
            if(gridDosNavios.getAcertosNave3P2() == 0){
                //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 3");
                naveDestruidas(gridDeBotoes, grid, 3, imgs.republicaNave3VerticalD, imgs.republicaNave3HorizontalD);
            }
            
        }
        if(grid[coluna][linha] == 4){
            gridDosNavios.calculaAcertosNave4P2();
            x[coluna][linha] = -4;
            if(gridDosNavios.getAcertosNave4P2() == 0){
                //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 4");
                naveDestruidas(gridDeBotoes, grid, 4, imgs.republicaNave4VerticalD, imgs.republicaNave4HorizontalD);
            }
            
        }
        if(grid[coluna][linha] == 5){
            gridDosNavios.calculaAcertosNave5P2();
            x[coluna][linha] = -5;
            if(gridDosNavios.getAcertosNave5P2() == 0){
                //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 5");
                naveDestruidas(gridDeBotoes, grid, 5, imgs.republicaNave5VerticalD, imgs.republicaNave5HorizontalD);
            }
            
        }
    }
    public void naveDestruidas(JButton gridDeBotoes[][],int x[][], int tamanhoDaNave, ImageIcon imgVertical[], ImageIcon imgHorizontal[]){
        int imagemDaNave = 0;
        int[] colunasDosAcertos = new int[tamanhoDaNave];
        int[] linhaDosAcertos = new int[tamanhoDaNave];
        // ImageIcon[] icones = new ImageIcon[10] ;
        // System.arraycopy(icones, 0, imagens, 0, imagens.length);
        for(int coluna = 0; coluna <= 9; coluna++){
        for(int linha = 0; linha <= 9; linha++){
            if(x[coluna][linha] == -tamanhoDaNave){
                colunasDosAcertos[imagemDaNave] = coluna;
                linhaDosAcertos[imagemDaNave] = linha;
                imagemDaNave++;
            }
        }
        }
        if(colunasDosAcertos[0] == colunasDosAcertos[1]){
            for(int i = 0; i < tamanhoDaNave; i++){
                gridDeBotoes[colunasDosAcertos[i]][linhaDosAcertos[i]].setDisabledIcon(imgHorizontal[i]);
            }
        }
        if(linhaDosAcertos[0] == linhaDosAcertos[1]){
            for(int i = 0; i < tamanhoDaNave; i++){
                gridDeBotoes[colunasDosAcertos[i]][linhaDosAcertos[i]].setDisabledIcon(imgVertical[i]);
            }
        }

    }

    public void recolocaNavesDestruidasP1(JButton gridDeBotoes[][], int grid[][], int coluna, int linha, Grid gridDosNavios){
        if(gridDosNavios.getAcertosNave2P1() == 0){
            //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 2");
            naveDestruidas(gridDeBotoes, grid, 2, imgs.impNave2VerticalD, imgs.impNave2HorizontalD);
            
        }
        if(gridDosNavios.getAcertosNave3P1() == 0){
            //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 3");
            naveDestruidas(gridDeBotoes, grid, 3, imgs.impNave3VerticalD, imgs.impNave3HorizontalD);
        }
        if(gridDosNavios.getAcertosNave4P1() == 0){
            //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 4");
            naveDestruidas(gridDeBotoes, grid, 4, imgs.impNave4VerticalD, imgs.impNave4HorizontalD);
        }
        if(gridDosNavios.getAcertosNave5P1() == 0){
            //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 5");
            naveDestruidas(gridDeBotoes, grid, 5, imgs.impNave5VerticalD, imgs.impNave5HorizontalD);
        }
    }

    public void recolocaNavesDestruidasP2(JButton gridDeBotoes[][],int grid[][], int coluna, int linha, Grid gridDosNavios){
        if(gridDosNavios.getAcertosNave2P2() == 0){
            //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 2");
            naveDestruidas(gridDeBotoes, grid, 2, imgs.republicaNave2VerticalD, imgs.republicaNave2HorizontalD);
        }
    
        if(gridDosNavios.getAcertosNave3P2() == 0){
            //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 3");
            naveDestruidas(gridDeBotoes, grid, 3, imgs.republicaNave3VerticalD, imgs.republicaNave3HorizontalD);
        }
        if(gridDosNavios.getAcertosNave4P2() == 0){
            //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 4");
            naveDestruidas(gridDeBotoes, grid, 4, imgs.republicaNave4VerticalD, imgs.republicaNave4HorizontalD);
        }
        
        if(gridDosNavios.getAcertosNave5P2() == 0){
            //JOptionPane.showMessageDialog(null, "Vc destroiu a nave de tamanho 5");
            naveDestruidas(gridDeBotoes, grid, 5, imgs.republicaNave5VerticalD, imgs.republicaNave5HorizontalD);
        }
    }
}