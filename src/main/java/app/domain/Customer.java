package app.domain;

//POJO - Plain Old Java Object
/*
POJO (Plain Old Java Object) — это обычный Java-объект,
который не зависит от каких-либо специфических фреймворков или интерфейсов.
Он может содержать любое количество полей и методов, которые не обязательно должны быть приватными или публичными.
POJO обычно используется для инкапсуляции бизнес-логики в приложениях
*/


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private  Long id;
    private boolean isActive;
    private String name;
    private final List<Product> products = new ArrayList<>();

    public Customer(boolean isActive, String name) {

        this.isActive = isActive;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return isActive == customer.isActive && Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(products, customer.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, name, products);
    }

    @Override
    public String
    toString() {
        final StringBuffer sb = new StringBuffer("Customer{");
        sb.append("id=").append(id);
        sb.append(", isActive=").append(isActive);
        sb.append(", name='").append(name).append('\'');
        sb.append(", products=").append(products);
        sb.append('}');
        return sb.toString();
    }
}
