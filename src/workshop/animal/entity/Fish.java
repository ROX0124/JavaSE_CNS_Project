package workshop.animal.entity;

public class Fish extends Animal implements Pet {
	private String name;
	
	public Fish() {
		super(0);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void play() {
		System.out.println("방안을 헤엄치는 중");
	}

	@Override
	public void eat() {
		System.out.println("크릴새우");
	}

}