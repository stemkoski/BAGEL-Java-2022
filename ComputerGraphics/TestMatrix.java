import graphics.math.*;

/**
 * Test methods from the Matrix class.
 */
public class TestMatrix
{
    public static void main(String[] args)
    {
        /*
        Matrix m = new Matrix(2,4);
        System.out.println( m );
        
        m.setValues(1, 2, 3, 4,
                    8, 7, 6, 5);
        System.out.println( m );
        
        Matrix n = new Matrix(2,2);
        n.setValues(1, 2,
                    3, 4 );
        Vector v = new Vector(5, 6);
        
        Vector w = n.multiplyVector(v);
        
        System.out.println( w.equals( new Vector(17, 39)) );
        
        Matrix A = new Matrix(2,2);
        A.setValues( 1, 2,
                     3, 4 );
        
        Matrix B = new Matrix(2,2);
        B.setValues( 5, 6,
                     2, 1 );
                     
        Matrix C = new Matrix(2,2);
        C.setValues(  9,  8,
                     23, 22 );
        System.out.println(C);
                     
        Matrix AB = Matrix.multiplyMatrices(A, B);
        System.out.println(AB);
        
        System.out.println( Matrix.multiplyMatrices(A, B).equals( C ) );
        */
        
        // testing translation matrix
        
        Matrix M = Matrix.makeTranslation(2, 3, 5);
        System.out.println(M);
        
        Matrix A = Matrix.makeIdentity();
        A = Matrix.multiplyMatrices(A, M);
        A = Matrix.multiplyMatrices(A, M);
        A = Matrix.multiplyMatrices(A, M);
        
        System.out.println( A );
       
       
       
       
       
       
       
       
       
       
       
       
    }
}
