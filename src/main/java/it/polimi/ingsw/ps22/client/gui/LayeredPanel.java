package it.polimi.ingsw.ps22.client.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LayeredPanel extends JPanel{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = -5630682030714330058L;

	private JPanel spinPan = new JPanel();
	
	private AllTowersPanel p;
	
	private ProductionHarvest hp;
	
	private FamiliarButton fam;
	
	final ImageIcon board = createImageIcon("./image/gameboard.jpg");
	
	//final ImageIcon card = createImageIcon("rsz_devcards_f_en_c_3.png");
	
	private JLayeredPane layeredPane;
	
	
	private JLabel mapIcon;
	private JLabel cardIcon;
	
	public double resizeFactor(ImageIcon c, double heightScreen){
		double factorScaleX = (double)c.getIconHeight() / (heightScreen);
		return factorScaleX;
	}
	
	public LayeredPanel(double widthScreen, double heightScreen) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	
		
		double factorScaleBoard = resizeFactor(board, heightScreen);
		
		layeredPane = new JLayeredPane();
		
		Image img = board.getImage();
		Rectangle dimBoard = new Rectangle(0,(int)(board.getIconWidth()/factorScaleBoard),0, (int)(board.getIconHeight()/factorScaleBoard));
		Image image = getScaledImage(img, dimBoard);
		ImageIcon board = new ImageIcon(image);
	
        mapIcon = new JLabel(board);
        mapIcon.setBounds(0, 0, board.getIconWidth(), board.getIconHeight());
       
        
        //cardIcon = new JLabel(card);
  
        //cardIcon.setBounds(200, 100, card.getIconWidth(), card.getIconHeight());
       
        
        layeredPane.add(mapIcon, new Integer(20), 0);
        //layeredPane.add(cardIcon, new Integer(25), 0);
		
        
		p = new AllTowersPanel();
		//hp = new ProductionHarvest();
		
		layeredPane.add(p, new Integer(200), 0);
		p.setBounds(150, 50, board.getIconWidth() - 200, board.getIconHeight()/2);
		 
		/*layeredPane.add(hp, new Integer(100),0);
		hp.setBounds(150,200 + board.getIconHeight()/2, board.getIconWidth()/2 -100, board.getIconHeight()-100);
		*/
	
		fam = new FamiliarButton(it.polimi.ingsw.ps22.server.model.Color.BLACK);
		fam.setBounds((int)widthScreen - 500, (int)heightScreen - 800, board.getIconWidth()- 400, board.getIconHeight() - 600);
		fam.addActionListener(new TakeFamiliarListener(p));
			
		layeredPane.add(fam, new Integer(50), 0);
		
		
		
		spinPan.setBounds((int)widthScreen - 500, (int)heightScreen - 500, board.getIconWidth()- 400, board.getIconHeight() - 700);
		spinFamiliar();
		
		this.setBackground(new Color(34,45,32));
		
		this.add(layeredPane);
        
        
	}
	
	public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = LayeredPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	/*private ImageIcon createScaledImageIcon(String path, double factorScale){
		ImageIcon icon = createImageIcon(path);
		Image image = icon.getImage();
		Rectangle dim = new Rectangle(0,0,(int)(icon.getIconWidth()/factorScale), (int)(icon.getIconHeight()/factorScale));
		Image img = getScaledImage(image, dim);
		return new ImageIcon(img);
	}*/
	
	private void spinFamiliar(){
		JLabel spinLab = new JLabel("familiar");
		spinPan.add(spinLab);
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);  
		JSpinner spinner = new JSpinner(model);
		spinLab.setLabelFor(spinner);
		spinPan.add(spinner);
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) spinner.getValue();
				p.setNumServants(value);
				
			}
		});
		layeredPane.add(spinPan, new Integer(2000),0);
	}

	private Image getScaledImage(Image srcImg, Rectangle dim){
	    BufferedImage resizedImg = new BufferedImage(dim.getOffsetX(), dim.getOffsetY(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, dim.getInitx(), dim.getInity(), dim.getFinalx(), dim.getFinaly(), null);
	    g2.dispose();

	    return resizedImg;
	}

	
}
