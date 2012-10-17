/**
 * 
 */
package main.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;

public class ScrollListModel extends AbstractListModel {

	private static final long serialVersionUID = 1L;
	List<String> model;
	private int spriteId;

	public ScrollListModel() {
		model = new ArrayList<String>();
	}

	public int getSpriteId() {
		return spriteId;
	}

	public void setSpriteId(int spriteId) {
		this.spriteId = spriteId;
	}

	public int getSize() {
		return model.size();
	}

	public String getElementAt(int index) {
		return model.get(index);
	}

	public void add(String element) {
		if (model.add(element)) {
			fireContentsChanged(this, 0, getSize());
		}
	}

	public void addAll(Object elements[]) {
		Collection<Object> c = Arrays.asList(elements);
		for(Object o:c) {
			model.add(o.toString());
		}
		fireContentsChanged(this, 0, getSize());
	}

	public void addAll(ScrollListModel elements) {
		Iterator<String> i = elements.iterator();
		while(i.hasNext())
			model.add((String) i.next());
		fireContentsChanged(this, 0, getSize());
	}

	public void clear() {
		model.clear();
		fireContentsChanged(this, 0, getSize());
	}

	public boolean contains(String element) {
		return model.contains(element);
	}

	public String firstElement() {
		return model.get(0);
	}

	public Iterator<String> iterator() {
		return model.iterator();
	}

	public String lastElement() {
		return model.get(model.size() - 1);
	}

	public boolean removeElement(Object element) {
		boolean removed = model.remove(element);
		if (removed) {
			fireContentsChanged(this, 0, getSize());
		}
		return removed;
	}
}