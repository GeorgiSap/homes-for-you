package agency;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import property.Property;
import property.Region;

public class Agency {
	private Set<Property> properties;

	public Agency() {
		properties = new HashSet<Property>();
	}

	public void addProperty(Property property) {
		if (property != null) {
			properties.add(property);
		}
	}
	
	public void removeProperty(Property property) {
		properties.remove(property);
	}
	
	public SortedSet<Property> searchBy(String city, Collection<String> buildingType, SortCriteria sortBy) {
		SortedSet<Property> result = new TreeSet<Property>(getComparator(sortBy));
		for (Property property : properties) {
			if (property.getCity().equals(city)) {
				for (String type : buildingType) {
					if (property.getPropertyType().equals(type)) {
						result.add(property);
						break;
					}
				}
			}
		}
		return result;
	}
	
	public SortedSet<Property> sortPropertiesBy(SortCriteria sortBy) {
		SortedSet<Property> result = new TreeSet<Property>(getComparator(sortBy));
		for (Property property : properties) {
			result.add(property);
		}
		return result;
	}
	
	private Comparator<Property> getComparator (SortCriteria sortBy) {
		switch (sortBy) {
		case NEWEST:
			return (p1, p2) -> {
				if (p1.getCreationDate().equals(p2.getCreationDate())) {
					return p1.getListingID() - p2.getListingID();
				}
				return p2.getCreationDate().compareTo(p1.getCreationDate());
		};
		case PRICE:
			return (p1, p2) -> {
				if (p1.getPrice() == p2.getPrice()) {
					return p1.getListingID() - p2.getListingID();
				}
				return (int)(p1.getPrice() - p2.getPrice());
		};
		case SQUARE_METER_PRICE:
			return (p1, p2) -> {
				if (p1.getPrice()/p1.getArea() == p2.getPrice()/p2.getArea()) {
					return p1.getListingID() - p2.getListingID();
				}
				return (int)(p1.getPrice()/p1.getArea() - p2.getPrice()/p2.getArea());
		};
		case REGION:
			return (p1, p2) -> {
				if (p1.getCity().equals(p2.getCity())) {
					return p1.getListingID() - p2.getListingID();
				}
				return p1.getCity().compareTo(p2.getCity());
		};
		default:
			return (p1, p2) -> p1.getListingID() - p2.getListingID();
		}
	}
}
