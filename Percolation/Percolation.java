import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] opened;
    private final int top;
    private final int bottom;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf2;
    private final int len;
    private int numOpenSites;  // set number of opensites as zero

    public Percolation(int N)
    {
        if (N <= 0) {
            throw new IllegalArgumentException("Given N <= 0");
        }
        opened = new boolean[N][N];
        uf     = new WeightedQuickUnionUF(N*N + 2);
        uf2    = new WeightedQuickUnionUF(N*N + 1);
        top    = N*N;
        bottom = N*N + 1;
        len    = N;
        numOpenSites = 0;

    }

    private boolean checkIndex(int i, int j)
    {
        if (i < 1 || i > len || j < 1 || j > len) {
            throw new IllegalArgumentException();
        }
        else{
            return true;
        }
    }

    public void open(int i, int j)
    {
        if (checkIndex(i, j)) {
            if (!isOpen(i, j)) {
                opened[i - 1][j - 1] = true;
                numOpenSites++;
                if (i == 1) {
                    uf.union(j - 1, top);
                    uf2.union(j - 1, top);
                }
                if (i == len) uf.union((i - 1) * len + j - 1, bottom);
                if (i > 1 && isOpen(i - 1, j)) {
                    uf.union((i - 1) * len + j - 1, (i - 2) * len + j - 1);
                    uf2.union((i - 1) * len + j - 1, (i - 2) * len + j - 1);
                }
                if (i < len && isOpen(i + 1, j)) {
                    uf.union((i - 1) * len + j - 1, i * len + j - 1);
                    uf2.union((i - 1) * len + j - 1, i * len + j - 1);
                }
                if (j > 1 && isOpen(i, j - 1)) {
                    uf.union((i - 1) * len + j - 1, (i - 1) * len + j - 2);
                    uf2.union((i - 1) * len + j - 1, (i - 1) * len + j - 2);
                }
                if (j < len && isOpen(i, j + 1)) {
                    uf.union((i - 1) * len + j - 1, (i - 1) * len + j);
                    uf2.union((i - 1) * len + j - 1, (i - 1) * len + j);
                }
            }
        }
    }

    public boolean isOpen(int i, int j)
    {
        if (checkIndex(i, j)) {
            return opened[i-1][j-1];
        }
        return false;
    }

    public boolean isFull(int i, int j)
    {
        if (checkIndex(i, j)) {
            return uf2.connected((i-1)*len+j-1, top);
        }
        return false;
    }

    public int numberOfOpenSites() {
        // number of open sites
        return numOpenSites;
    }

    public boolean percolates()
    {
        return uf.connected(top, bottom);
    }

}