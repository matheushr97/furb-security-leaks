package br.com.furb.trabalho1;

import javafx.beans.property.SimpleStringProperty;

public class Person {
	
	private final SimpleStringProperty name;

    public Person(String name) {
        this.name = new SimpleStringProperty(name);
    }
	
    public String getNome() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }	

}
