package controller;

public class CustomSystem extends ecs.System {

	protected IController controller;
	
	public CustomSystem() {}
	
	public CustomSystem(IController controller) {
		this.setController(controller);
	}
	
	public void setController(IController controller) {
		this.controller = controller;
	}
}
