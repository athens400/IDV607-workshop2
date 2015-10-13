public class Program {

	public static void main(String[] args) {
		
		model.MemberRegistry memberRegistry = new model.MemberRegistry();
		memberRegistry.importList();
		controller.MemberController controller = new controller.MemberController(memberRegistry);
		controller.start();
		memberRegistry.exportList();
	}

}
