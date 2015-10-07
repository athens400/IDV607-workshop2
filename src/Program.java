public class Program {

	public static void main(String[] args) {
		
		model.MemberRegistry m = new model.MemberRegistry();
		m.importList();
		controller.Member c = new controller.Member(m);
		c.start();
		m.exportList();
	}

}
