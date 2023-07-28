package ru.nsu.fit.bozhko;

public class Matrix {
    private double[] data;
    private int rows, cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    public Matrix(int rows, int cols, double ... elements) {
        if(elements.length != rows * cols)
            throw new IllegalArgumentException();
        this.rows = rows;
        this.cols = cols;

        data = elements;
    }

    public static Matrix getScaleMatrix(double sx, double sy, double sz) {
        return new Matrix(4, 4,
                sx, 0, 0, 0,
                0, sy, 0,0,
                0, 0, sz, 0,
                0, 0, 0, 1);
    }

    public static Matrix getViewMatrix(Point3D eye, Point3D ref, Point3D up) {
        Point3D w = new Point3D(eye.getX() - ref.getX(), eye.getY() - ref.getY(),
                eye.getZ() - ref.getZ()).normalize();
        Point3D upw = Point3D.getVectorProduct(up, w);
        Point3D u = upw.normalize();
        Point3D v = Point3D.getVectorProduct(w, u);

        return new Matrix(4, 4,
                u.getX(), u.getY(), u.getZ(), - (u.getX() * eye.getX() + u.getY() * eye.getY() + u.getZ() * eye.getZ()),
                v.getX(), v.getY(), v.getZ(), -(v.getX() * eye.getX() + v.getY() * eye.getY() + v.getZ() * eye.getZ()),
                w.getX(), w.getY(), w.getZ(), -(w.getX() * eye.getX() + w.getY() * eye.getY() + w.getZ() * eye.getZ()),
                0, 0, 0, 1);
    }

    public static Matrix getProjectionMatrix(double sw, double sh, double zf, double zn) {
        return new Matrix(4, 4,
                2 / sw * zn, 0, 0, 0,
                0, 2 / sh * zn, 0, 0,
                0, 0, zn /(zf - zn), -zn * zf / (zf - zn),
                0, 0, 1, 0);
    }

    public static Matrix add(Matrix M1, Matrix M2) {
        double[] m1d = M1.data;
        double[] m2d = M2.data;
        if(m1d.length != m2d.length || M1.cols != M2.cols)
            throw new IllegalArgumentException();

        Matrix result = new Matrix(M1.rows, M1.cols);

        double[] nd = result.data;
        for(int i = 0; i < m1d.length; i++) {
            nd[i] = m1d[i] + m2d[i];
        }
        return result;
    }

    public static Matrix multiply(Matrix M1, Matrix M2) {
        Matrix result = new Matrix(M1.rows, M2.cols);
        double[] m1d = M1.data;
        double[] m2d = M2.data;
        double[] nd = result.data;

        int l = M1.rows;
        int m = M1.cols;
        int n = M2.cols;

        for(int i = 0; i < l; i++)
            for(int j = 0; j < n;j++)
                for(int k = 0; k < m; k++)
                    nd[i * n + j] += m1d[i * m + k] * m2d[k * n + j];

        return result;
    }

    public double get(int row, int col) {
        return data[row * cols + col];
    }

}
