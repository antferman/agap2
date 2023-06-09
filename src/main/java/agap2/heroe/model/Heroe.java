package agap2.heroe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Heroe {

	public Heroe() {

	}

	public Heroe(int id, String name) {
		this.id=id;
		this.name=name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Heroe [id=" + id + ", name=" + name + "]";
	}

	@Id
	@Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

}
