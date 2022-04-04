package graphics.math;

/**
 * The Matrix class stores a grid of numbers, and defines operations on matrices.
 */
public class Matrix
{
    public double[][] values;
    public int rows;
    public int columns;

    public Matrix(int rows, int columns)
    {
        values = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    // requires rows x columns inputs (all doubles)
    // setValues(...v) is varargs : variable number of arguments.
    // can use with any number of arguments: setValues(1,2,3,4,5,6)
    // all the arguments are then packed into an array
    //   for use in the function.
    public void setValues(double ...newValues)
    {
        for (int i = 0; i < newValues.length; i++)
        {
            double v = newValues[i];
            int rowNum = i / columns;
            int columnNum = i % columns;
            values[rowNum][columnNum] = v;
        }
    }

    public Vector getRow(int rowNum)
    {
        // each row has one entry from each column of the matrix
        Vector v = new Vector(columns);
        for (int i = 0; i < columns; i++)
        {
            v.values[i] = this.values[rowNum][i];
        }
        return v;
    }

    public Vector getColumn(int columnNum)
    {   
        // each column has one entry from each row of the matrix
        Vector v = new Vector(rows);
        for (int i = 0; i < rows; i++)
        {
            v.values[i] = this.values[i][columnNum];
        }
        return v;
    }

    public String toString()
    {
        String s = "";
        for (int i = 0; i < rows; i++)
            s += getRow(i).toString() + "\n";
        return s;   
    }

    /**
     *  Multiply this matrix by a vector.
     */
    public Vector multiplyVector(Vector v)
    {
        Vector w = new Vector(rows);

        for (int i = 0; i < rows; i++)
            w.values[i] = Vector.dot( getRow(i), v );

        return w;
    }

    /**
     * Multiply this matrix times another matrix.
     * Calculates (this * other).
     * @param other the other matrix to multiply by.
     */
    public Matrix multiplyMatrix(Matrix other)
    {
        // create a new matrix to store values from calculation
        Matrix M = new Matrix(this.rows, other.columns);

        for (int rowNum = 0; rowNum < this.rows; rowNum++)
        {
            Vector row = this.getRow(rowNum);
            for (int columnNum = 0; columnNum < other.columns; columnNum++)
            {
                Vector column = other.getColumn(columnNum);
                double number = Vector.dot( row, column );
                M.values[rowNum][columnNum] = number;
            }
        }

        return M;
    }

    /**
     * Multiply two matrices and return a new matrix that is their product.
     *
     * @param A first matrix
     * @param B second matrix
     * @return the matrix product A * B
     */
    public static Matrix multiplyMatrices(Matrix A, Matrix B)
    {
        // create a new matrix to store values from calculation
        Matrix M = new Matrix(A.rows, B.columns);

        for (int rowNum = 0; rowNum < A.rows; rowNum++)
        {
            Vector row = A.getRow(rowNum);
            for (int columnNum = 0; columnNum < B.columns; columnNum++)
            {
                Vector column = B.getColumn(columnNum);
                double number = Vector.dot( row, column );
                M.values[rowNum][columnNum] = number;
            }
        }

        return M;
    }

    public boolean equals(Matrix other)
    {    
        boolean same = true;
        for (int i = 0; i < this.rows; i++)
        {
            for (int j = 0; j < this.columns; j++)
            {
                same = same && (this.values[i][j] == other.values[i][j]);
            }
        }
        return same;
    }

    // convert 2D array of values into a 1D array of (float) values
    public float[] flatten()
    {
        float[] array = new float[rows * columns];
        int index = 0;
        for (int columnNum = 0; columnNum < columns; columnNum++)
        {
            for (int rowNum = 0; rowNum < rows; rowNum++)
            {
                array[ index ] = (float)values[rowNum][columnNum];
                index++;
            }
        }
        return array;
    }

    // represents no translation, no rotation.
    public static Matrix makeIdentity()
    {
        Matrix M = new Matrix(4,4);
        M.setValues( 1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1  );
        return M;
    }

    public static Matrix makeTranslation(double dx, double dy, double dz)
    {
        Matrix M = new Matrix(4,4);
        M.setValues( 
            1, 0, 0, dx,
            0, 1, 0, dy,
            0, 0, 1, dz,
            0, 0, 0,  1  );
        return M;
    }
    
    public static Matrix makeRotationZ(double angle)
    {
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        Matrix M = new Matrix(4,4);
        M.setValues( 
            c, -s, 0, 0,
            s,  c, 0, 0,
            0,  0, 1, 0,
            0,  0, 0, 1  );
        return M;
    }

    public static Matrix makeScale(double s)
    {
        Matrix M = new Matrix(4,4);
        M.setValues( 
            s, 0, 0, 0,
            0, s, 0, 0,
            0, 0, s, 0,
            0, 0, 0, 1  );
        return M;
    }
    
    // new methods --------------------------
    
    public static Matrix makeRotationX(double angle)
    {
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        Matrix m = new Matrix(4,4);
        m.setValues(1, 0,  0, 0, 
                    0, c, -s, 0, 
                    0, s,  c, 0, 
                    0, 0,  0, 1);
        return m;
    }

    public static Matrix makeRotationY(double angle)
    {
        double c = Math.cos(angle);
        double s = Math.sin(angle);
        Matrix m = new Matrix(4,4);
        m.setValues( c, 0, s, 0, 
                     0, 1, 0, 0, 
                    -s, 0, c, 0, 
                     0, 0, 0, 1);
        return m;
    }
    
    public static Matrix makePerspective(
          double angleOfView, double aspectRatio, double near, double far)
    {
        double a = Math.toRadians(angleOfView);
        double d = 1.0 / Math.tan(a/2);
        double r = aspectRatio;
        double b = (far + near) / (near - far);
        double c = 2*far*near / (near - far);
        Matrix m = new Matrix(4,4);
        m.setValues(d/r,0,0,0, 0,d,0,0, 0,0,b,c, 0,0,-1,0);
        return m;
    }
    
    // default parameters
    public static Matrix makePerspective()
    {
        return makePerspective(60, 1, 0.1, 1000);
    }

    // replace this matrix with (M * this)
    public void leftMultiply(Matrix M)
    {
        this.values = Matrix.multiplyMatrices(M, this).values;
    }

    // replace this matrix with (this * M)
    public void rightMultiply(Matrix M)
    {
        this.values = Matrix.multiplyMatrices(this, M).values;
    }
    
     // inverse related methods; require square matrix
    public double determinant() 
    {
        // if (rows != cols)
        //     throw new Exception();
        if (rows == 1)
            return values[0][0];
        
        if (rows == 2)
            return values[0][0] * values[1][1] - values[0][1] * values[1][0];

        // for larger matrices, calculate determinant 
        //   using cofactor expansion along first row
        double det = 0;
        for (int colNum = 0; colNum < columns; colNum++)
            det += Math.pow(-1, colNum) * values[0][colNum] * minor(0, colNum).determinant();
        return det;
    }

    // generate (rows-1) by (cols-1) submatrix, excluding given row and col.
    public Matrix minor(int excludeRowNum, int excludeColNum)
    {
        Matrix m = new Matrix(this.rows-1, this.columns-1);

        int minorRowNum = 0, minorColNum = 0;
        for (int rowNum = 0; rowNum < rows; rowNum++)
        {
            if (rowNum == excludeRowNum)
                continue;

            minorColNum = 0;
            for (int colNum = 0; colNum < columns; colNum++)
            {
                if (colNum == excludeColNum)
                    continue;

                m.values[minorRowNum][minorColNum] = this.values[rowNum][colNum];
                minorColNum++;
            }
            minorRowNum++;
        }

        return m;
    }

    public void multiplyScalar(double s)
    {
        for (int rowNum = 0; rowNum < rows; rowNum++)
            for (int colNum = 0; colNum < columns; colNum++)
                values[rowNum][colNum] *= s;
    }

    public Matrix transpose()
    {
        Matrix tr = new Matrix(this.columns, this.rows);
        for (int rowNum = 0; rowNum < rows; rowNum++)
            for (int colNum = 0; colNum < columns; colNum++)
                tr.values[colNum][rowNum] = this.values[rowNum][colNum];
        return tr;
    }

    // this is a computationally heavy operation!
    public Matrix inverse()
    {
        Matrix inv = new Matrix(rows, columns);

        for (int rowNum = 0; rowNum < rows; rowNum++)
            for (int colNum = 0; colNum < columns; colNum++)
                inv.values[rowNum][colNum] = Math.pow(-1, rowNum + colNum)
                    * this.minor(rowNum, colNum).determinant();

        double det = determinant();
        inv = inv.transpose();
        inv.multiplyScalar( 1.0f/det );
        return inv;
    }

}
