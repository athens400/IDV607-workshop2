package model;

import java.util.ArrayList;
import java.util.List;

public class MemberRegistry {

	private List<Member> members;
	
	public MemberRegistry() {
		members = new ArrayList<Member>();
	}
	
	public MemberRegistry(List<Member> membersList) {
		members = membersList;
	}
	
	public void importList() {
		persistence.Storage storage = new persistence.Storage();
		List<Member> members = storage.load();
		if(members != null) {
			this.members = members;
		}
	}
	
	public void exportList() {
		persistence.Storage storage = new persistence.Storage();
		storage.save(members);
	}
	
	public void addMember(String name, long pNr) {
		int id = getNextId();
		Member newMember = new Member(id, name, pNr);
		members.add(newMember);
	}
	
	public boolean deleteMember(int id, boolean resetIds) {
		Member member = getMember(id);
		if(member != null) {
			members.remove(member);
			if(resetIds) {
				resetMemberIds();
			}
			return true;
		}
		return false;
	}
	
	public Member getMember(int id) {
		for(Member member : members) {
			if(id == member.getId()) {
				return member;
			}
		}
		return null;
	}
	
	public void listMembers() {
		for(Member member : members) {
			System.out.println(member.getName());
		}
	}
	
	public List<Member> getMembers() {
		return members;
	}
	
	private int getNextId() {
		int nextId = 1;
		if(members.size() != 0) {
			Member lastMember = members.get(members.size() - 1);
			nextId = lastMember.getId() + 1;
		}
		return nextId;
	}
	
	private void resetMemberIds() {
		int id = 1;
		for(Member member : members) {
			member.setId(id);
			++id;
		}
	}
	
}
