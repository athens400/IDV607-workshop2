package persistence;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

public class Storage {
	
	public String file = "members.dat";

	public void save(List<model.Member> toSave) {
		try {
		ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(file));
		out.writeObject(toSave);
		out.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<model.Member> load() {
		try {
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(file));
		@SuppressWarnings("unchecked")
		List<model.Member> toLoad = (ArrayList<model.Member>) in.readObject();
		in.close();
		return toLoad;
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (FileNotFoundException ex) {
			// Do nothing
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
