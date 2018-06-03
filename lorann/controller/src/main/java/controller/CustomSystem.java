package controller;

public abstract class CustomSystem extends ecs.System {

	protected IController controller;
	
	public CustomSystem() {
		super();
	}
	
	public CustomSystem(IController controller) {
		super();
		this.setController(controller);
	}
	
	public void setController(IController controller) {
		this.controller = controller;
	}
}
