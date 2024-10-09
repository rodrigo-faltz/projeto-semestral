package projeto;

import javax.swing.*;
import java.awt.*;
public class Imagens {
    
    public ImageIcon imperio, republica, batalha, botaoProximo, botaoComoJogar, botaoNovoJogo, botaoCarregar, botaoSalvar, botaoProsseguir, botaoSair, acerto, erro, mira, fundo, naoPode, vitoria1, vitoria2,
    comojogar1, comojogar2, comojogar3, comojogar4, comojogar5, comojogar6, comojogar7, comojogar8, comojogar[], republicaNave2Vertical1, republicaNave2Vertical2, republicaNave2Vertical[], republicaNave2Horizontal1,republicaNave2Horizontal2, 
    republicaNave2Horizontal[],republicaNave3Horizontal1, republicaNave3Horizontal2, republicaNave3Horizontal3, republicaNave3Horizontal[], republicaNave3Vertical1,
    republicaNave3Vertical2, republicaNave3Vertical3, republicaNave3Vertical[], republicaNave4Vertical1, republicaNave4Vertical2, republicaNave4Vertical3, 
    republicaNave4Vertical4, republicaNave4Vertical[],  republicaNave4Horizontal1,  republicaNave4Horizontal2,  republicaNave4Horizontal3,  republicaNave4Horizontal4,  
    republicaNave4Horizontal[], republicaNave5Horizontal1, republicaNave5Horizontal2, republicaNave5Horizontal3, republicaNave5Horizontal4, republicaNave5Horizontal5,
    republicaNave5Horizontal[], republicaNave5Vertical1, republicaNave5Vertical2, republicaNave5Vertical3, republicaNave5Vertical4, republicaNave5Vertical5,
    republicaNave5Vertical[], impNave2Horizontal1, impNave2Horizontal2, impNave2Horizontal[], impNave2Vertical1, impNave2Vertical2, impNave2Vertical[],
    impNave3Horizontal1, impNave3Horizontal2, impNave3Horizontal3, impNave3Horizontal[], impNave3Vertical1, impNave3Vertical2, impNave3Vertical3, impNave3Vertical[],
    impNave4Vertical1, impNave4Vertical2, impNave4Vertical3, impNave4Vertical4, impNave4Vertical[], impNave4Horizontal1, impNave4Horizontal2, impNave4Horizontal3,
    impNave4Horizontal4, impNave4Horizontal[], impNave5Horizontal1, impNave5Horizontal2, impNave5Horizontal3, impNave5Horizontal4, impNave5Horizontal5, impNave5Horizontal[],
    impNave5Vertical1, impNave5Vertical2, impNave5Vertical3, impNave5Vertical4, impNave5Vertical5, impNave5Vertical[], republicaNave2Vertical1D, republicaNave2Vertical2D,
    republicaNave2VerticalD[], republicaNave2Horizontal1D, republicaNave2Horizontal2D, republicaNave2Horizontal3D, republicaNave2HorizontalD[], republicaNave3Horizontal1D,
    republicaNave3Horizontal2D, republicaNave3Horizontal3D, republicaNave3HorizontalD[], republicaNave3Vertical1D, republicaNave3Vertical2D, republicaNave3Vertical3D,
    republicaNave3VerticalD[], republicaNave4Horizontal1D, republicaNave4Horizontal2D, republicaNave4Horizontal3D, republicaNave4Horizontal4D, republicaNave4HorizontalD[],
    republicaNave4Vertical1D, republicaNave4Vertical2D, republicaNave4Vertical3D, republicaNave4Vertical4D, republicaNave4VerticalD[], republicaNave5Vertical1D, 
    republicaNave5Vertical2D, republicaNave5Vertical3D, republicaNave5Vertical4D, republicaNave5Vertical5D, republicaNave5VerticalD[], republicaNave5Horizontal1D,
    republicaNave5Horizontal2D, republicaNave5Horizontal3D, republicaNave5Horizontal4D, republicaNave5Horizontal5D, republicaNave5HorizontalD[], impNave2Horizontal1D,
    impNave2Horizontal2D, impNave2HorizontalD[], impNave2Vertical1D, impNave2Vertical2D, impNave2VerticalD[], impNave3Horizontal1D, impNave3Horizontal2D, impNave3Horizontal3D,
    impNave3HorizontalD[], impNave3Vertical1D, impNave3Vertical2D, impNave3Vertical3D, impNave3VerticalD[], impNave4Vertical1D, impNave4Vertical2D, impNave4Vertical3D,
    impNave4Vertical4D, impNave4VerticalD[], impNave4Horizontal1D, impNave4Horizontal2D, impNave4Horizontal3D, impNave4Horizontal4D, impNave4HorizontalD[],
    impNave5Horizontal1D, impNave5Horizontal2D, impNave5Horizontal3D, impNave5Horizontal4D, impNave5Horizontal5D, impNave5HorizontalD[], impNave5Vertical1D, impNave5Vertical2D,
    impNave5Vertical3D, impNave5Vertical4D, impNave5Vertical5D, impNave5VerticalD[];

    public Color corDoFundo;

    public Imagens(){

        vitoria1 = new ImageIcon("lib\\vitoria2.gif");
        vitoria2 = new ImageIcon("lib\\vitoria1.gif");
        republica = new ImageIcon("lib\\republica.png");
        imperio = new ImageIcon("lib\\imperio.png");
        comojogar1 = new ImageIcon("lib\\comoJogarTelaInicial.jpg");
        
        comojogar2 = new ImageIcon("lib\\comoJogar2.jpg");
        comojogar3 = new ImageIcon("lib\\comoJogar3.jpg");
        comojogar4 = new ImageIcon("lib\\comoJogar4.jpg");
        comojogar5 = new ImageIcon("lib\\comoJogar5.jpg");
        comojogar6 = new ImageIcon("lib\\comoJogar6.jpg");
        comojogar7 = new ImageIcon("lib\\comoJogar7.jpg");
        comojogar8 = new ImageIcon("lib\\comoJogar8.jpg");

        comojogar = new ImageIcon[]{comojogar1, comojogar2, comojogar3, comojogar4, comojogar5, comojogar6, comojogar7, comojogar8};
        batalha = new ImageIcon("lib\\Bataalha.jpg");
        corDoFundo = new Color(7,8,28);
        // fundo = new ImageIcon("lib\\Untitled-1.jpg");
        botaoSair = new ImageIcon("lib\\BotaoSair.png");
        botaoProximo = new ImageIcon("lib\\BotaoProximo.png");
        botaoSalvar = new ImageIcon("lib\\BotaoSalvar.png");
        botaoCarregar = new ImageIcon("lib\\carregarJogo.png");
        botaoNovoJogo = new ImageIcon("lib\\novoJogoButton.png");
        botaoComoJogar = new ImageIcon("lib\\comoJogarButton.png");
        botaoProsseguir = new ImageIcon("lib\\BotaoProsseguir.png");
        acerto = new ImageIcon("lib\\explodiu.jpg");
        erro = new ImageIcon("lib\\Errou.jpg");
        fundo = new ImageIcon("lib\\VSFD.jpg");
        naoPode = new ImageIcon("lib\\NaoPode.jpg");
        mira = new ImageIcon("lib\\target-modified.jpg");
        republicaNave2Vertical1 = new ImageIcon("lib\\RepNaveP2Vertical1.jpg");
        republicaNave2Vertical2 = new ImageIcon("lib\\RepNaveP2Vertical2.jpg");
        republicaNave2Vertical = new ImageIcon[]{republicaNave2Vertical1, republicaNave2Vertical2};

        republicaNave2Horizontal1 = new ImageIcon("lib\\RepNaveP2Horizontal1.jpg");
        republicaNave2Horizontal2 = new ImageIcon("lib\\RepNaveP2Horizontal2.jpg");
        republicaNave2Horizontal = new ImageIcon[]{republicaNave2Horizontal1, republicaNave2Horizontal2};

        republicaNave3Horizontal1 = new ImageIcon("lib\\RepNaveP3Horizontal1.jpg");
        republicaNave3Horizontal2 = new ImageIcon("lib\\RepNaveP3Horizontal2.jpg");
        republicaNave3Horizontal3 = new ImageIcon("lib\\RepNaveP3Horizontal3.jpg");
        republicaNave3Horizontal = new ImageIcon[]{republicaNave3Horizontal1, republicaNave3Horizontal2, republicaNave3Horizontal3};

        republicaNave3Vertical1 = new ImageIcon("lib\\RepNaveP3Vertical1.jpg");
        republicaNave3Vertical2 = new ImageIcon("lib\\RepNaveP3Vertical2.jpg");
        republicaNave3Vertical3 = new ImageIcon("lib\\RepNaveP3Vertical3.jpg");
        republicaNave3Vertical = new ImageIcon[]{republicaNave3Vertical1, republicaNave3Vertical2, republicaNave3Vertical3};

        republicaNave4Horizontal1 = new ImageIcon("lib\\RepNaveP4Horizontal1.jpg");
        republicaNave4Horizontal2 = new ImageIcon("lib\\RepNaveP4Horizontal2.jpg");
        republicaNave4Horizontal3 = new ImageIcon("lib\\RepNaveP4Horizontal3.jpg");
        republicaNave4Horizontal4 = new ImageIcon("lib\\RepNaveP4Horizontal4.jpg");
        republicaNave4Horizontal = new ImageIcon[]{republicaNave4Horizontal1, republicaNave4Horizontal2, republicaNave4Horizontal3, republicaNave4Horizontal4};

        republicaNave4Vertical1 = new ImageIcon("lib\\RepNaveP4Vertical1.jpg");
        republicaNave4Vertical2 = new ImageIcon("lib\\RepNaveP4Vertical2.jpg");
        republicaNave4Vertical3 = new ImageIcon("lib\\RepNaveP4Vertical3.jpg");
        republicaNave4Vertical4 = new ImageIcon("lib\\RepNaveP4Vertical4.jpg");
        republicaNave4Vertical = new ImageIcon[]{republicaNave4Vertical1, republicaNave4Vertical2, republicaNave4Vertical3, republicaNave4Vertical4};

        republicaNave5Vertical1 = new ImageIcon("lib\\RepNaveP5Vertical1.jpg");
        republicaNave5Vertical2 = new ImageIcon("lib\\RepNaveP5Vertical2.jpg");
        republicaNave5Vertical3 = new ImageIcon("lib\\RepNaveP5Vertical3.jpg");
        republicaNave5Vertical4 = new ImageIcon("lib\\RepNaveP5Vertical4.jpg");
        republicaNave5Vertical5 = new ImageIcon("lib\\RepNaveP5Vertical5.jpg");
        republicaNave5Vertical = new ImageIcon[]{republicaNave5Vertical1, republicaNave5Vertical2, republicaNave5Vertical3, republicaNave5Vertical4, republicaNave5Vertical5};

        republicaNave5Horizontal1 = new ImageIcon("lib\\RepNaveP5Horizontal1.jpg");
        republicaNave5Horizontal2 = new ImageIcon("lib\\RepNaveP5Horizontal2.jpg");
        republicaNave5Horizontal3 = new ImageIcon("lib\\RepNaveP5Horizontal3.jpg");
        republicaNave5Horizontal4 = new ImageIcon("lib\\RepNaveP5Horizontal4.jpg");
        republicaNave5Horizontal5 = new ImageIcon("lib\\RepNaveP5Horizontal5.jpg");
        republicaNave5Horizontal = new ImageIcon[]{republicaNave5Horizontal1, republicaNave5Horizontal2, republicaNave5Horizontal3, republicaNave5Horizontal4, republicaNave5Horizontal5};

        impNave2Horizontal1 = new ImageIcon("lib\\impNaveP2Horizontal1.jpg");
        impNave2Horizontal2 = new ImageIcon("lib\\impNaveP2Horizontal2.jpg");
        impNave2Horizontal = new ImageIcon[]{impNave2Horizontal1, impNave2Horizontal2};

        impNave2Vertical1 = new ImageIcon("lib\\impNaveP2Vertical1.jpg");
        impNave2Vertical2 = new ImageIcon("lib\\impNaveP2Vertical2.jpg");
        impNave2Vertical = new ImageIcon[]{impNave2Vertical1, impNave2Vertical2};

        impNave3Horizontal1 = new ImageIcon("lib\\impNaveP3Horizontal1.jpg");
        impNave3Horizontal2 = new ImageIcon("lib\\impNaveP3Horizontal2.jpg");
        impNave3Horizontal3 = new ImageIcon("lib\\impNaveP3Horizontal3.jpg");
        impNave3Horizontal = new ImageIcon[]{impNave3Horizontal1, impNave3Horizontal2, impNave3Horizontal3};

        impNave3Vertical1 = new ImageIcon("lib\\impNaveP3Vertical1.jpg");
        impNave3Vertical2 = new ImageIcon("lib\\impNaveP3Vertical2.jpg");
        impNave3Vertical3 = new ImageIcon("lib\\impNaveP3Vertical3.jpg");
        impNave3Vertical = new ImageIcon[]{impNave3Vertical1, impNave3Vertical2, impNave3Vertical3};

        impNave4Vertical1 = new ImageIcon("lib\\impNaveP4Vertical1.jpg");
        impNave4Vertical2 = new ImageIcon("lib\\impNaveP4Vertical2.jpg");
        impNave4Vertical3 = new ImageIcon("lib\\impNaveP4Vertical3.jpg");
        impNave4Vertical4 = new ImageIcon("lib\\impNaveP4Vertical4.jpg");
        impNave4Vertical = new ImageIcon[]{impNave4Vertical1, impNave4Vertical2, impNave4Vertical3, impNave4Vertical4};

        impNave4Horizontal1 = new ImageIcon("lib\\impNaveP4Horizontal1.jpg");
        impNave4Horizontal2 = new ImageIcon("lib\\impNaveP4Horizontal2.jpg");
        impNave4Horizontal3 = new ImageIcon("lib\\impNaveP4Horizontal3.jpg");
        impNave4Horizontal4 = new ImageIcon("lib\\impNaveP4Horizontal4.jpg");
        impNave4Horizontal = new ImageIcon[]{impNave4Horizontal1, impNave4Horizontal2, impNave4Horizontal3, impNave4Horizontal4};

        impNave5Horizontal1 = new ImageIcon("lib\\impNaveP5Horizontal1.jpg");
        impNave5Horizontal2 = new ImageIcon("lib\\impNaveP5Horizontal2.jpg");
        impNave5Horizontal3 = new ImageIcon("lib\\impNaveP5Horizontal3.jpg");
        impNave5Horizontal4 = new ImageIcon("lib\\impNaveP5Horizontal4.jpg");
        impNave5Horizontal5 = new ImageIcon("lib\\impNaveP5Horizontal5.jpg");
        impNave5Horizontal = new ImageIcon[]{impNave5Horizontal1, impNave5Horizontal2, impNave5Horizontal3, impNave5Horizontal4, impNave5Horizontal5};

        impNave5Vertical1 = new ImageIcon("lib\\impNaveP5Vertical1.jpg");
        impNave5Vertical2 = new ImageIcon("lib\\impNaveP5Vertical2.jpg");
        impNave5Vertical3 = new ImageIcon("lib\\impNaveP5Vertical3.jpg");
        impNave5Vertical4 = new ImageIcon("lib\\impNaveP5Vertical4.jpg");
        impNave5Vertical5 = new ImageIcon("lib\\impNaveP5Vertical5.jpg");
        impNave5Vertical = new ImageIcon[]{impNave5Vertical1, impNave5Vertical2, impNave5Vertical3, impNave5Vertical4, impNave5Vertical5};

        //-------------------------------------------------

        republicaNave2Vertical1D = new ImageIcon("lib\\RepNaveP2Vertical1D.jpg");
        republicaNave2Vertical2D = new ImageIcon("lib\\RepNaveP2Vertical2D.jpg");
        republicaNave2VerticalD = new ImageIcon[]{republicaNave2Vertical1D, republicaNave2Vertical2D};

        republicaNave2Horizontal1D = new ImageIcon("lib\\RepNaveP2Horizontal1D.jpg");
        republicaNave2Horizontal2D = new ImageIcon("lib\\RepNaveP2Horizontal2D.jpg");
        republicaNave2HorizontalD = new ImageIcon[]{republicaNave2Horizontal1D, republicaNave2Horizontal2D};

        republicaNave3Horizontal1D = new ImageIcon("lib\\RepNaveP3Horizontal1D.jpg");
        republicaNave3Horizontal2D = new ImageIcon("lib\\RepNaveP3Horizontal2D.jpg");
        republicaNave3Horizontal3D = new ImageIcon("lib\\RepNaveP3Horizontal3D.jpg");
        republicaNave3HorizontalD = new ImageIcon[]{republicaNave3Horizontal1D, republicaNave3Horizontal2D, republicaNave3Horizontal3D};

        republicaNave3Vertical1D = new ImageIcon("lib\\RepNaveP3Vertical1D.jpg");
        republicaNave3Vertical2D = new ImageIcon("lib\\RepNaveP3Vertical2D.jpg");
        republicaNave3Vertical3D = new ImageIcon("lib\\RepNaveP3Vertical3D.jpg");
        republicaNave3VerticalD = new ImageIcon[]{republicaNave3Vertical1D, republicaNave3Vertical2D, republicaNave3Vertical3D};

        republicaNave4Horizontal1D = new ImageIcon("lib\\RepNaveP4Horizontal1D.jpg");
        republicaNave4Horizontal2D = new ImageIcon("lib\\RepNaveP4Horizontal2D.jpg");
        republicaNave4Horizontal3D = new ImageIcon("lib\\RepNaveP4Horizontal3D.jpg");
        republicaNave4Horizontal4D = new ImageIcon("lib\\RepNaveP4Horizontal4D.jpg");
        republicaNave4HorizontalD = new ImageIcon[]{republicaNave4Horizontal1D, republicaNave4Horizontal2D, republicaNave4Horizontal3D, republicaNave4Horizontal4D};

        republicaNave4Vertical1D = new ImageIcon("lib\\RepNaveP4Vertical1D.jpg");
        republicaNave4Vertical2D = new ImageIcon("lib\\RepNaveP4Vertical2D.jpg");
        republicaNave4Vertical3D = new ImageIcon("lib\\RepNaveP4Vertical3D.jpg");
        republicaNave4Vertical4D = new ImageIcon("lib\\RepNaveP4Vertical4D.jpg");
        republicaNave4VerticalD = new ImageIcon[]{republicaNave4Vertical1D, republicaNave4Vertical2D, republicaNave4Vertical3D, republicaNave4Vertical4D};

        republicaNave5Vertical1D = new ImageIcon("lib\\RepNaveP5Vertical1D.jpg");
        republicaNave5Vertical2D = new ImageIcon("lib\\RepNaveP5Vertical2D.jpg");
        republicaNave5Vertical3D = new ImageIcon("lib\\RepNaveP5Vertical3D.jpg");
        republicaNave5Vertical4D = new ImageIcon("lib\\RepNaveP5Vertical4D.jpg");
        republicaNave5Vertical5D = new ImageIcon("lib\\RepNaveP5Vertical5D.jpg");
        republicaNave5VerticalD = new ImageIcon[]{republicaNave5Vertical1D, republicaNave5Vertical2D, republicaNave5Vertical3D, republicaNave5Vertical4D, republicaNave5Vertical5D};

        republicaNave5Horizontal1D = new ImageIcon("lib\\RepNaveP5Horizontal1D.jpg");
        republicaNave5Horizontal2D = new ImageIcon("lib\\RepNaveP5Horizontal2D.jpg");
        republicaNave5Horizontal3D = new ImageIcon("lib\\RepNaveP5Horizontal3D.jpg");
        republicaNave5Horizontal4D = new ImageIcon("lib\\RepNaveP5Horizontal4D.jpg");
        republicaNave5Horizontal5D = new ImageIcon("lib\\RepNaveP5Horizontal5D.jpg");
        republicaNave5HorizontalD = new ImageIcon[]{republicaNave5Horizontal1D, republicaNave5Horizontal2D, republicaNave5Horizontal3D, republicaNave5Horizontal4D, republicaNave5Horizontal5D};

        impNave2Horizontal1D = new ImageIcon("lib\\impNaveP2Horizontal1D.jpg");
        impNave2Horizontal2D = new ImageIcon("lib\\impNaveP2Horizontal2D.jpg");
        impNave2HorizontalD = new ImageIcon[]{impNave2Horizontal1D, impNave2Horizontal2D};

        impNave2Vertical1D = new ImageIcon("lib\\impNaveP2Vertical1D.jpg");
        impNave2Vertical2D = new ImageIcon("lib\\impNaveP2Vertical2D.jpg");
        impNave2VerticalD = new ImageIcon[]{impNave2Vertical1D, impNave2Vertical2D};

        impNave3Horizontal1D = new ImageIcon("lib\\impNaveP3Horizontal1D.jpg");
        impNave3Horizontal2D = new ImageIcon("lib\\impNaveP3Horizontal2D.jpg");
        impNave3Horizontal3D = new ImageIcon("lib\\impNaveP3Horizontal3D.jpg");
        impNave3HorizontalD = new ImageIcon[]{impNave3Horizontal1D, impNave3Horizontal2D, impNave3Horizontal3D};

        impNave3Vertical1D = new ImageIcon("lib\\impNaveP3Vertical1D.jpg");
        impNave3Vertical2D = new ImageIcon("lib\\impNaveP3Vertical2D.jpg");
        impNave3Vertical3D = new ImageIcon("lib\\impNaveP3Vertical3D.jpg");
        impNave3VerticalD = new ImageIcon[]{impNave3Vertical1D, impNave3Vertical2D, impNave3Vertical3D};

        impNave4Vertical1D = new ImageIcon("lib\\impNaveP4Vertical1D.jpg");
        impNave4Vertical2D = new ImageIcon("lib\\impNaveP4Vertical2D.jpg");
        impNave4Vertical3D = new ImageIcon("lib\\impNaveP4Vertical3D.jpg");
        impNave4Vertical4D = new ImageIcon("lib\\impNaveP4Vertical4D.jpg");
        impNave4VerticalD = new ImageIcon[]{impNave4Vertical1D, impNave4Vertical2D, impNave4Vertical3D, impNave4Vertical4D};

        impNave4Horizontal1D = new ImageIcon("lib\\impNaveP4Horizontal1D.jpg");
        impNave4Horizontal2D = new ImageIcon("lib\\impNaveP4Horizontal2D.jpg");
        impNave4Horizontal3D = new ImageIcon("lib\\impNaveP4Horizontal3D.jpg");
        impNave4Horizontal4D = new ImageIcon("lib\\impNaveP4Horizontal4D.jpg");
        impNave4HorizontalD = new ImageIcon[]{impNave4Horizontal1D, impNave4Horizontal2D, impNave4Horizontal3D, impNave4Horizontal4D};

        impNave5Horizontal1D = new ImageIcon("lib\\impNaveP5Horizontal1D.jpg");
        impNave5Horizontal2D = new ImageIcon("lib\\impNaveP5Horizontal2D.jpg");
        impNave5Horizontal3D = new ImageIcon("lib\\impNaveP5Horizontal3D.jpg");
        impNave5Horizontal4D = new ImageIcon("lib\\impNaveP5Horizontal4D.jpg");
        impNave5Horizontal5D = new ImageIcon("lib\\impNaveP5Horizontal5D.jpg");
        impNave5HorizontalD = new ImageIcon[]{impNave5Horizontal1D, impNave5Horizontal2D, impNave5Horizontal3D, impNave5Horizontal4D, impNave5Horizontal5D};

        impNave5Vertical1D = new ImageIcon("lib\\impNaveP5Vertical1D.jpg");
        impNave5Vertical2D = new ImageIcon("lib\\impNaveP5Vertical2D.jpg");
        impNave5Vertical3D = new ImageIcon("lib\\impNaveP5Vertical3D.jpg");
        impNave5Vertical4D = new ImageIcon("lib\\impNaveP5Vertical4D.jpg");
        impNave5Vertical5D = new ImageIcon("lib\\impNaveP5Vertical5D.jpg");
        impNave5VerticalD = new ImageIcon[]{impNave5Vertical1D, impNave5Vertical2D, impNave5Vertical3D, impNave5Vertical4D, impNave5Vertical5D};

    }
}
