package SparseVector;

import java.util.Vector;

class Node																	 
{
	int index;
	double value;
	Node next;

	public Node(int index, double value, Node next)
	{
		this.index = index;
		this.value = value;
		this.next = next;
	}
}

public class SparseVector
{
	private Vector<Integer> Vec;
	private Node head;
	private int length;

	public SparseVector(Vector<Integer> Vector)
	{			
		this.Vec = Vector;
	}

	public SparseVector(int n)
	{ 
		this.head = initializeVector(n);
		this.length = (n < 0) ? 0 : n;
	}

	private Node initializeVector(int n)
	{
		Node dummy = new Node(0, 0.0, null);
		Node current = dummy;

		for (int i = 1; i < n; i++)
		{
			current.next = new Node(i, 0.0, null);
			current = current.next;
		}

		return dummy.next;
	}

	public void setElement(int index, double value)
	{
		if (index < 0 || index >= length) 
		{
			throw new IllegalArgumentException("Index out of bounds");
		}
		if (head == null || index < head.index)
		{
			head = new Node(index, value, head);
			return;
		}
		
		Node current = head;
		
		while (current.next != null && current.next.index < index) 
		{
			current = current.next;
		}

		if (current.next != null && current.next.index == index)
		{
			current.next.value = value;
		} 
		else
		{
			current.next = new Node(index, value, current.next);
		}
	}

	public double getElement(int index)
	{
		if (index < 0 || index >= length)
		{
			throw new IllegalArgumentException("Index out of bounds");
		}

		Node current = head;

		while (current != null && current.index < index)
		{
			current = current.next;
		}
		
		return (current != null && current.index == index) ? current.value : 0.0;
	}


	public void removeElement(int index)
	{
		if (index < 0 || index >= length)
		{
			throw new IllegalArgumentException("Index out of bounds");
		}
		if (head != null && index == head.index)
		{
			head = head.next;
		} 
		else
		{
			Node current = head;

			while (current != null && current.next != null && current.next.index < index)
			{
				current = current.next;
			}
			
			if (current != null && current.next != null && current.next.index == index)
			{
				current.next = current.next.next;
			}
		}
	}

	
	public int getLength()
	{
		return length;
	}

	
	public boolean equals(SparseVector other)
	{
	    
	    if (this.length != other.length)
	    {
	        return false;
	    }

	    Node currentThis = this.head;
	    Node currentOther = other.head;

	    while (currentThis != null && currentOther != null) 
	    {
	        if (currentThis.index != currentOther.index || currentThis.value != currentOther.value)
	        {
	            return false;
	        }
	        currentThis = currentThis.next;
	        currentOther = currentOther.next;
	    }
	    return true;
	}

	
	public void add(SparseVector other)
	{
	    if (this.length != other.length) 
	    {
	        throw new IllegalArgumentException("Vektoren haben unterschiedliche Längen");
	    }

	    Node currentThis = this.head;
	    Node currentOther = other.head;
	    
	    while (currentThis != null && currentOther != null)
	    {
	        currentThis.value += currentOther.value;
	        currentThis = currentThis.next;
	        currentOther = currentOther.next;
	    }
	}
}
