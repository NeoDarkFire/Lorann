package controller;

public class CustomSystem extends System {

	protected IController controller;
	
	public CustomSystem(IController controller) {
		this.controller = controller;
	}
}
