package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile implements ITile {

	private TileSolidity solidity;
	private Image image;

	/**
	 * Creates a new Tile with an Image and a TileSolidity.
	 * @param filename The path to the Image.
	 * @param solidity A TileSolidity.
	 * @see TileSolidity
	 */
	public Tile(String filename , TileSolidity solidity) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(filename).getFile());
			this.image = ImageIO.read(file);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		this.solidity = solidity;
	}
	
	/**
	 * Get the Image of the Tile.
	 * @return An Image.
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Get the solidity of the Tile.
	 * @return A TileSolidity.
	 */
	public TileSolidity getSolidity() {
		return solidity;
		
	}
}