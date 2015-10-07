package controller;

public class Member {
	
	private model.MemberRegistry m_reg;
	private view.Console a_view;
	
	public Member(model.MemberRegistry m_reg) {
		this.m_reg = m_reg;
		a_view = new view.Console();
	}
	
	public void start() {
		a_view.displayWelcome();
		while(executeEvent());
	}
	
	public boolean executeEvent() {
		a_view.displayMenu();
		view.Console.Event e = a_view.getEvent();
		
		switch(e) {
		case NEW_MEMBER:
			newMember();
			break;
		case DELETE_MEMBER:
			deleteMember();
			break;
		case EDIT_MEMBER:
			editMember();
			break;
		case VIEW_MEMBER:
			viewMember();
			break;
		case LIST_MEMBERS_COMPACT:
			listMembers(false);
			break;
		case LIST_MEMBERS_VERBOSE:
			listMembers(true);
			break;
		case REGISTER_BOAT:
			registerBoat();
			break;
		case DELETE_BOAT:
			deleteBoat();
			break;
		case EDIT_BOAT:
			editBoat();
			break;
		case QUIT:
			return false;
		default:
			break;
		}
		return true;
	}
	
	private void newMember() {
		String name = a_view.getName();
		long pNr = a_view.getPNr();
		m_reg.addMember(name, pNr);
		a_view.success();
	}
	
	private void deleteMember() {
		listMembers(false);
		int memberId = a_view.whichMember();
		boolean result = m_reg.deleteMember(memberId, false);
		if(result) {
			a_view.success();
		}
		else {
			a_view.failMember();
		}
	}
	
	private void editMember() {
		listMembers(false);
		int memberId = a_view.whichMember();
		model.Member member = m_reg.getMember(memberId);
		if(member != null) {
			String name = a_view.getName();
			long pNr = a_view.getPNr();
			member.setName(name);
			member.setPNr(pNr);
			a_view.success();
		}
		else {
			a_view.failMember();
		}
	}
	
	private void viewMember() {
		listMembers(false);
		int memberId = a_view.whichMember();
		model.Member member = m_reg.getMember(memberId);
		if(member != null) {
			a_view.displayMemberVerbose(member);
			listBoats(member);
		}
		else {
			a_view.failMember();
		}
	}
	
	private void listMembers(boolean verbose) {
		for(model.Member member : m_reg.getMembers()) {
			if(verbose) {
				a_view.displayMemberVerbose(member);
				listBoats(member);
			}
			else {
				a_view.displayMemberCompact(member);
			}
		}
	}
	
	private void listBoats(model.Member member) {
		int i = 1;
		for(model.Boat boat : member.getBoats()) {
			a_view.displayBoat(boat, i);
			++i;
		}
	}
	
	private void registerBoat() {
		listMembers(false);
		int memberId = a_view.whichMember();
		model.Member member = m_reg.getMember(memberId);
		if(member != null) {
			a_view.displayBoatTypeMenu();
			model.Boat.BoatType type = a_view.getBoatType();
			double length = a_view.getBoatLength();
			member.addBoat(type, length);
			a_view.success();
		}
		else {
			a_view.failMember();
		}
	}
	
	private void deleteBoat() {
		boolean result = true;
		listMembers(false);
		int memberId = a_view.whichMember();
		model.Member member = m_reg.getMember(memberId);
		if(member != null) {
			listBoats(member);
			int boatNr = a_view.whichBoat();
			result = member.deleteBoat(boatNr);
			if(result) {
				a_view.success();
			}
			else {
				a_view.failBoat();
			}
		}
		else {
			a_view.failMember();
		}
	}
	
	private void editBoat() {
		listMembers(false);
		int memberId = a_view.whichMember();
		model.Member member = m_reg.getMember(memberId);
		if(member != null) {
			listBoats(member);
			int boatNr = a_view.whichBoat();
			model.Boat boat = member.getBoat(boatNr);
			if(boat != null) {
				a_view.displayBoatTypeMenu();
				model.Boat.BoatType type = a_view.getBoatType();
				double length = a_view.getBoatLength();
				boat.setType(type);
				boat.setLength(length);
				a_view.success();
			}
			else {
				a_view.failBoat();
			}		
		}
		else {
			a_view.failMember();
		}
	}

}
