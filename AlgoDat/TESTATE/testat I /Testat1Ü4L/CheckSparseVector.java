package SparseVector;

public class CheckSparseVector
{
    public static boolean testConstructors() 
    {
        SparseVector vector = new SparseVector(0);
        if (!(vector.getLength() == 0))
        {
            System.out.println("Constructor Test 1 Failed");
            return false;
        }

        vector = new SparseVector(5);
        if (!(vector.getLength() == 5))
        {
            System.out.println("Constructor Test 2 Failed");
            return false;
        }

        return true;
    }

    public static boolean testSetElement()
    {
        SparseVector vector = new SparseVector(5);
        vector.setElement(0, 1.0);
        vector.setElement(2, 2.0);
        vector.setElement(4, 3.0);
        if (!(vector.getElement(0) == 1.0 && vector.getElement(2) == 2.0 && vector.getElement(4) == 3.0))
        {
            System.out.println("SetElement Test Failed");
            return false;
        }

        return true;
    }

    public static boolean testGetElement()
    {
        SparseVector vector = new SparseVector(5);
        vector.setElement(0, 1.0);
        vector.setElement(2, 2.0);
        vector.setElement(4, 3.0);
        if (!(vector.getElement(1) == 0.0 && vector.getElement(3) == 0.0))
        {
            System.out.println("GetElement Test Failed");
            return false;
        }

        return true;
    }

    public static boolean testRemoveElement() 
    {
        SparseVector vector = new SparseVector(5);
        vector.setElement(0, 1.0);
        vector.setElement(2, 2.0);
        vector.setElement(4, 3.0);
        vector.removeElement(2);
        if (!(vector.getElement(2) == 0.0))
        {
            System.out.println("RemoveElement Test Failed");
            return false;
        }

        return true;
    }

    public static boolean testGetLength() 
    {
        SparseVector vector = new SparseVector(5);
        if (!(vector.getLength() == 5))
        {
            System.out.println("GetLength Test Failed");
            return false;
        }

        return true;
    }

    public static boolean testEquals()
    {
        SparseVector vector1 = new SparseVector(5);
        SparseVector vector2 = new SparseVector(5);
        vector1.setElement(0, 1.0);
        vector1.setElement(4, 3.0);
        vector2.setElement(0, 1.0);
        vector2.setElement(4, 3.0);
        if (!vector1.equals(vector2))
        {
            System.out.println("Equals Test Failed");
            return false;
        }

        return true;
    }

    public static boolean testAdd() 
    {
        SparseVector vector1 = new SparseVector(5);
        SparseVector vector2 = new SparseVector(5);
        vector1.setElement(0, 1.0);
        vector1.setElement(4, 3.0);
        vector2.setElement(0, 1.0);
        vector2.setElement(4, 3.0);
        vector1.add(vector2);
        if (!(vector1.getElement(0) == 2.0 && vector1.getElement(4) == 6.0))
        {
            System.out.println("Add Test Failed");
            return false;
        }

        return true;
    }
    
    public static void main(String[] args)
    {
    	
    	if(testConstructors() && testSetElement() && testGetElement() && testRemoveElement() && testGetLength() && testEquals() && testAdd())
    	{
            System.out.println("All tests passed!");
        } 
    	else 
    	{
            System.out.println("A test has failed!");
        }
    }

}