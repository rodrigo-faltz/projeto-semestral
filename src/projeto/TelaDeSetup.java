package projeto;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import projeto.Message.Action;
import java.awt.*;
import java.io.*;
import java.net.*;

import java.util.logging.*;


public class TelaDeSetup extends JFrame implements ActionListener, MouseListener
{
    
	private JButton botoesDoGrid[][], botaoDeBaixo;
    private JPanel painel1, painel2;
    String ultimoString, antString, answString, op ;
    int orientacao, numeroDeNavios, tamnhoDoNavioAtual;
    double ant, answ;
    Imagens imgs;
    int x[][];
    private Grid gridInstance;
    Player player;
    private Socket socket;
    private ClienteService service;
    private Message message;
    
    

	
    public TelaDeSetup(Player player, Grid gridInstance, Socket socket, ClienteService service) {
        
		super("Batalha Espacial - Preparação das Naves");
        this.player = player;
        this.gridInstance = gridInstance;
        this.socket = socket;
        this.service = service;
        
        
        
        JOptionPane.showMessageDialog(null, "Coloque as suas naves no tabuleiro");
        imgs = new Imagens();
        x = new int[10][10]; 
        numeroDeNavios = 0; //Numero de navios ja colocados
        tamnhoDoNavioAtual = 2;

        orientacao = 0; // Orientacao de como a nave vai ser colocada (0 = horizontal, 1 = vertical)


        //Inicializa o GUI
        Container caixa = getContentPane();
        caixa.setLayout(new BorderLayout());
        painel1 = new JPanel(new GridLayout(10,10));
        painel2 = new JPanel(new FlowLayout());
        botoesDoGrid = new JButton[10][10];
        botaoDeBaixo = new JButton(imgs.botaoProsseguir);
        botaoDeBaixo.setContentAreaFilled(false);
        botaoDeBaixo.setBorderPainted(false);
        criaGrid(botoesDoGrid, painel1);

        //Adiciona os bglho um no outro
        botaoDeBaixo.setPreferredSize(new Dimension(170, 50));
        painel2.add(botaoDeBaixo);
        botaoDeBaixo.addActionListener(this);
        botaoDeBaixo.addMouseListener(this);
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
        if((e.getSource() == botaoDeBaixo)&&(numeroDeNavios ==4)){
            
            Message message = new Message();
            
            gridInstance.setGridDoPlayer(x);
            // TODO: para enviar a senha pro servidor vai ser parecido com isso
            //dispose();
            message.setGrid(gridInstance);
            message.setAction(Action.ENVIA_GRID); //leva pro servidor
            message.setPlayer(player);
            service.envia(message);
            new Thread(new ListenerSocket(this.socket)).start();
            new TelaAposSetup(gridInstance, socket);
            
            dispose();
            
        }
        else if((e.getSource() == botaoDeBaixo)){
            JOptionPane.showMessageDialog(null, "Coloque todas as naves");
        }
        for (int coluna = 0; coluna<10;coluna++){
        for (int linha = 0; linha<10;linha++){
                if(e.getSource()==botoesDoGrid[coluna][linha]){
                    if(isColocacaoHorizontalValida(orientacao, tamnhoDoNavioAtual, coluna, linha, x) && numeroDeNavios < 4){
                        colocaNavioHorizontal(botoesDoGrid, tamnhoDoNavioAtual, coluna, linha, x);
                        numeroDeNavios++;
                        tamnhoDoNavioAtual++;
                    }
                    else if(isColocacaoVerticalValida(orientacao, tamnhoDoNavioAtual, coluna, linha, x)&& numeroDeNavios < 4){
                        colocaNavioVertical(botoesDoGrid, tamnhoDoNavioAtual, coluna, linha, x);
                        numeroDeNavios++;
                        tamnhoDoNavioAtual++;
                    }
                    else if(numeroDeNavios >= 4){
                        JOptionPane.showMessageDialog(null, "Atingiu o limite de naves");
                    }
                }
        }
        }
    }
    public void mouseEntered(MouseEvent e) { //Quando um mouse entra em um botao ele executa isso
        switch (orientacao) {

            case 0: //Quando o modo de colocar for horizontal
                if(tamnhoDoNavioAtual < 6){
                    for (int coluna = 0; coluna<10;coluna++){
                    for (int linha = 0; linha<10;linha++){
                        if(e.getSource()==botoesDoGrid[coluna][linha]){
                            previaDoNavioHorizontal(coluna, linha, botoesDoGrid, tamnhoDoNavioAtual);
                        }
                    }
                    }
                }
                break;

            case 1: //Quando o modo de colocar for vertical
            if(tamnhoDoNavioAtual < 6){
                for (int o = 0; o<10;o++){
                for (int p = 0; p<10;p++){
                    if(e.getSource()==botoesDoGrid[o][p]){
                        previaDoNavioVertical(o, p, botoesDoGrid, tamnhoDoNavioAtual);
                    }
                }
                }
            }
                break;
        }
    }
    public void mouseExited(MouseEvent e) { // Quando o mouse sai de um botao ele executa isso
        switch (orientacao) {
            case 0: 
            if(tamnhoDoNavioAtual < 6){
                for (int coluna = 0; coluna<10;coluna++){
                for (int linha = 0; linha<10;linha++){
                    if(e.getSource()==botoesDoGrid[coluna][linha]){ 
                        removePreviaHorizontal(coluna, linha, botoesDoGrid, tamnhoDoNavioAtual);
                    }
                }
                }
            }
                break;
            case 1:
            if(tamnhoDoNavioAtual < 6){
                for (int o = 0; o<10;o++){
                for (int p = 0; p<10;p++){
                    if(e.getSource()==botoesDoGrid[o][p]){ //Acho q isso daqui da pra simplifica ent vou comentar dps
                        removePreviaVertical(o, p, botoesDoGrid, tamnhoDoNavioAtual);
                    }
                }
                }
            }
                break;
        }
    }
    
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) { // Quando o botao direito for apertado 
            if(orientacao<1){ 
                orientacao = orientacao+1;
            }
            else{
                orientacao = 0;
            }
            for (int coluna = 0; coluna<10;coluna++){
                for (int linha = 0; linha<10;linha++){
                        botoesDoGrid[coluna][linha].setIcon(imgs.fundo); //Reseta a cor do quadrados
                        
                }
            }
        }
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {

    }
    public void criaGrid(JButton buttons[][], JPanel panel){ //Cria o grid de botoes e adiciona ele ao painel e os listeners
        for(int coluna = 0; coluna < 10; coluna++){
            for(int linha = 0; linha<10;linha++){
                buttons[coluna][linha] = new JButton(imgs.fundo);
                panel.add(botoesDoGrid[coluna][linha]);
                buttons[coluna][linha].setBorderPainted(false);
                buttons[coluna][linha].addActionListener(this);
                buttons[coluna][linha].addMouseListener(this);
            }
        }
    }
    //PRONTO
    public boolean isColocacaoHorizontalValida(int orientacaoDoNavio, int tamanhoDoNavio, int coluna, int linha, int x[][]){ //Verifica se eh possivel colocar a nave onde o usuario quer
        boolean teste = true;
        if(orientacaoDoNavio == 0 && linha < 11-tamanhoDoNavio){
            for(int i =0; i < tamanhoDoNavio; i++){
                if(x[coluna][linha+i] == 0){
                    teste = false || teste;
                }
                else{
                    teste = false;
                }
            }
            return teste;
        }
        else{
            return false;
        }
    }
    //PRONTO
    public boolean isColocacaoVerticalValida(int orientacaoDoNavio, int tamanhoDoNavio, int coluna, int linha, int x[][]){ //Verifica se eh possivel colocar a nave onde o usuario quer
        boolean teste = true;
        if(orientacaoDoNavio == 1 && coluna < 11-tamanhoDoNavio){
            for(int i =0; i < tamanhoDoNavio; i++){
                if(x[coluna+i][linha] == 0){
                    teste = false || teste;
                }
                else{
                    teste = false;
                }
            }
            return teste;
        }
        else{
            return false;
        }
    }

    public void colocaNavioHorizontal(JButton gridDeBotoes[][], int tamanhoDaNave,int coluna, int linha, int x[][]){ //Funcao para colocar a nave no grid
        for(int i = 0; i < tamanhoDaNave; i++){
            gridDeBotoes[coluna][linha+i].setDisabledIcon(imagemCertaHorizontal(tamanhoDaNave)[i]);
            gridDeBotoes[coluna][linha+i].setOpaque(false);
            gridDeBotoes[coluna][linha+i].setContentAreaFilled(false);
            gridDeBotoes[coluna][linha+i].setBorderPainted(false);
            gridDeBotoes[coluna][linha+i].setEnabled(false);
            
            x[coluna][linha+i] = tamanhoDaNave;
        }
    }

    public void colocaNavioVertical(JButton gridDeBotoes[][], int tamanhoDaNave,int coluna, int linha, int x[][]){ //Funcao para colocar a nave no grid
        for(int i = 0; i < tamanhoDaNave; i++){
            gridDeBotoes[coluna+i][linha].setDisabledIcon(imagemCertaVertical(tamanhoDaNave)[i]);
            botoesDoGrid[coluna+i][linha].setOpaque(false);
            botoesDoGrid[coluna+i][linha].setContentAreaFilled(false);
            botoesDoGrid[coluna+i][linha].setBorderPainted(false);
            botoesDoGrid[coluna+i][linha].setEnabled(false);
            x[coluna+i][linha] = tamanhoDaNave;
        }
    }

    public void previaDoNavioVertical(int coluna, int linha, JButton botoesDoGrid[][], int tamanhoDaNave){ //Faz os botoes mudar de cor de acorod com a validade da colocacao do barco
        for(int i = 9; i > 10-tamanhoDaNave; i--){
            if(coluna == i){
                for(int j = 0; j < 10-i; j++){
                    botoesDoGrid[coluna+j][linha].setIcon(imgs.naoPode); 
                }
            }
        }
        if(temNavioNoMeioDaPreviaVertical(tamanhoDaNave, x, coluna, linha)){
            for(int j = 0; j < tamanhoDaNave; j++){
                pintaLugarDoBarcoVerticalImagem(tamanhoDaNave, coluna, linha, imgs.naoPode);
            }
        }
        else if(coluna < 11-tamanhoDaNave){
            
            pintaLugarDoBarcoVerticalImagemArray(tamanhoDaNave, coluna, linha, imagemCertaVertical(tamanhoDaNave));
            
            //pintaLugarDoBarcoVertical(tamanhoDaNave, coluna, linha, Color.GREEN);
        }

    }
    //PRONTO
    public boolean temNavioNoMeioDaPreviaVertical(int tamanhoDaNave, int x[][], int coluna, int linha){ //Verifica tem barco no meio da previa
        boolean teste = false;
        for(int i = 0; i < tamanhoDaNave; i++){
            if(coluna < 11-tamanhoDaNave){
                teste = (x[coluna+i][linha] > 1) || teste;
            }
            
        }
        return teste;
    }

    public void pintaLugarDoBarcoVertical(int tamanhoDaNave, int coluna, int linha, Color cor){ //Muda cor dos botoes
        for(int j = 0; j < tamanhoDaNave; j++){
            botoesDoGrid[coluna+j][linha].setBackground(cor); 
        }
    }

    public void pintaLugarDoBarcoVerticalImagem(int tamanhoDaNave, int coluna, int linha, ImageIcon cor){ //Muda cor dos botoes
        for(int j = 0; j < tamanhoDaNave; j++){
            botoesDoGrid[coluna+j][linha].setIcon(cor); 
            botoesDoGrid[coluna+j][linha].setContentAreaFilled(false);
        }
    }

    public void pintaLugarDoBarcoVerticalImagemArray(int tamanhoDaNave, int coluna, int linha, ImageIcon cor[]){ //Muda cor dos botoes
        for(int j = 0; j < tamanhoDaNave; j++){
            botoesDoGrid[coluna+j][linha].setIcon(cor[j]); 
            botoesDoGrid[coluna+j][linha].setContentAreaFilled(false);
        }
    }

    public void previaDoNavioHorizontal(int coluna, int linha, JButton botoesDoGrid[][], int tamanhoDaNave){ //Faz os botoes mudar de cor de acorod com a validade da colocacao do barco
        for(int i = 9; i > 10-tamanhoDaNave; i--){
            if(linha == i){
                for(int j = 0; j < 10-i; j++){
                    botoesDoGrid[coluna][linha+j].setIcon(imgs.naoPode); 
                }
            }
        }
        if(temNavioNoMeioDaPreviaHorizontal(tamanhoDaNave, x, coluna, linha)){ 
            for(int j = 0; j < tamanhoDaNave; j++){
                pintaLugarDoBarcoHorizontalImagem(tamanhoDaNave, coluna, linha, imgs.naoPode);
            }
        }
        else if(linha < 11-tamanhoDaNave){
            pintaLugarDoBarcoHorizontalImagemArray(tamanhoDaNave, coluna, linha, imagemCertaHorizontal(tamanhoDaNave));
        }

    }

    public boolean temNavioNoMeioDaPreviaHorizontal(int tamanhoDaNave, int x[][], int coluna, int linha){ //Verifica tem barco no meio da previa
        boolean teste = false;
        for(int i = 0; i < tamanhoDaNave; i++){
            if(linha < 11-tamanhoDaNave){
                teste = (x[coluna][linha+i] > 1) || teste;
            }
            
        }
        return teste;
    }

    public void pintaLugarDoBarcoHorizontal(int tamanhoDaNave, int coluna, int linha, Color cor){ //Muda cor dos botoes
        for(int j = 0; j < tamanhoDaNave; j++){
            botoesDoGrid[coluna][linha+j].setBackground(cor); 
        }
    }


    public void pintaLugarDoBarcoHorizontalImagem(int tamanhoDaNave, int coluna, int linha, ImageIcon cor){ //Muda cor dos botoes
        for(int j = 0; j < tamanhoDaNave; j++){
            botoesDoGrid[coluna][linha+j].setIcon(cor); 
        }
    }
    public void pintaLugarDoBarcoHorizontalImagemArray(int tamanhoDaNave, int coluna, int linha, ImageIcon cor[]){ //Muda cor dos botoes
        for(int j = 0; j < tamanhoDaNave; j++){
            botoesDoGrid[coluna][linha+j].setIcon(cor[j]); 
        }
    }

    public void removePreviaHorizontal(int coluna, int linha, JButton botoesDoGrid[][], int tamanhoDaNave){
        if(0 <= linha && linha <= 10-tamanhoDaNave){
            pintaLugarDoBarcoHorizontalImagem(tamanhoDaNave, coluna, linha, imgs.fundo);
        }
        else{
            for(int i = 9; i > 10-tamanhoDaNave; i--){
                if(linha == i){
                    for(int j = 0; j < 10-i; j++){
                        botoesDoGrid[coluna][linha+j].setIcon(imgs.fundo);
                    }
                }
            }
        }
    }

    public void removePreviaVertical(int coluna, int linha, JButton botoesDoGrid[][], int tamanhoDaNave){
        if(0 <= coluna && coluna <= 10-tamanhoDaNave){
            pintaLugarDoBarcoVerticalImagem(tamanhoDaNave, coluna, linha, imgs.fundo);
        }
        else{
            for(int i = 9; i > 10-tamanhoDaNave; i--){
                if(coluna == i){
                    for(int j = 0; j < 10-i; j++){
                        botoesDoGrid[coluna+j][linha].setIcon(imgs.fundo); 
                    }
                }
            }
        }
    }

    public ImageIcon[] imagemCertaHorizontal(int tamanhoDaNave){
        
        if(player.getNumero() == 1){
            switch (tamanhoDaNave) {
                case 2:
                    return imgs.republicaNave2Horizontal;
                case 3:
                    return imgs.republicaNave3Horizontal;
                case 4:
                    return imgs.republicaNave4Horizontal;
                case 5:
                    return imgs.republicaNave5Horizontal;
                default:
                    return imgs.republicaNave3Horizontal;
                }
        }
        else{
                switch (tamanhoDaNave) {
                    case 2:
                        return imgs.impNave2Horizontal;
                    case 3:
                        return imgs.impNave3Horizontal;
                    case 4:
                        return imgs.impNave4Horizontal;
                    case 5:
                        return imgs.impNave5Horizontal;
                    default:
                        return imgs.republicaNave3Horizontal;
                }
        }
    }

    public ImageIcon[] imagemCertaVertical(int tamanhoDaNave){
        
        if(player.getNumero() == 1){
            switch (tamanhoDaNave) {
                case 2:
                    return imgs.republicaNave2Vertical;
                case 3:
                    return imgs.republicaNave3Vertical;
                case 4:
                    return imgs.republicaNave4Vertical;
                case 5:
                    return imgs.republicaNave5Vertical;
                default:
                    return imgs.republicaNave3Vertical;
            }
        } else{
            switch (tamanhoDaNave) {
                case 2:
                    return imgs.impNave2Vertical;
                case 3:
                    return imgs.impNave3Vertical;
                case 4:
                    return imgs.impNave4Vertical;
                case 5:
                    return imgs.impNave5Vertical;
                default:
                    return imgs.republicaNave3Vertical;
            }
        }

        
    }

    private class ListenerSocket implements Runnable
    {
        private ObjectInputStream input;

        public ListenerSocket(Socket socket)
        {
            try {
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        @Override
        public void run()
        {
            Message message = null;
            try
                {
                    while ((message = (Message) input.readObject())!=null)
                        {
                            Action action = message.getAction();

                            if(action.equals(Action.ENVIA_PLAYER))
                            {
                                player.setNumero(message.getNumeroDoPlayer());
                                System.out.println("Recebeu o player: "+message.getNumeroDoPlayer());
                            }

                            if(action.equals(Action.ENVIA_GRID))
                            {
                                gridInstance =message.getGrid();
                            }

                            if(action.equals(Action.ENVIA_VITÓRIA))
                            {
                                System.out.println("Como que recebe vitória se nem começou?");
                            }

                        }

                }
            catch(IOException e)
            {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }
            catch(ClassNotFoundException e)
            {
                Logger.getLogger(ServidorService.class.getName()).log(Level.SEVERE, null, e);
            }

        }
    }
    
}

