package assignment1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private int[][] grid;
  private WeightedQuickUnionUF ufTree;
  private int size;
  private int topNode;
  private int bottomNode;

  public Percolation(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException();
    }
    int ufSize = (size * size) + 2;

    this.size = size;
    grid = new int[size][size];
    ufTree = new WeightedQuickUnionUF(ufSize);
    topNode = 0;
    bottomNode = (size * size) + 1;
    for (int i = 0; i <= size; i++) {
      ufTree.union(topNode, i);
      ufTree.union(bottomNode, bottomNode - i);
    }
  }

  public void open(int i, int j) {
    int row = i - 1;
    int column = j - 1;
    if (row < size && column < size) {
      grid[row][column] = 1;
    } else {
      throw new IndexOutOfBoundsException();
    }
    int ufNode = getTreeIndex(row, column);
    connect(ufNode, row - 1, column);
    connect(ufNode, row + 1, column);
    connect(ufNode, row, column - 1);
    connect(ufNode, row, column + 1);
  }

  private void connect(int ufNode, int row, int column) {
    try {
      if (grid[row][column] == 1) {
        ufTree.union(ufNode, getTreeIndex(row, column));
      }
    } catch (IndexOutOfBoundsException e) {
      return;
    }
  }

  public boolean isOpen(int i, int j) {
    int row = i - 1;
    int column = j - 1;
    if (row < size && column < size) {
      return grid[row][column] == 1;
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public boolean isFull(int i, int j) {
    int row = i - 1;
    int column = j - 1;
    if (row < size && column < size) {
      if (isOpen(i, j)) {
        int index = getTreeIndex(row, column);
        return ufTree.connected(topNode, index);
      } else {
        return false;
      }
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  private int getTreeIndex(int row, int column) {
    if (column == 0) {
      return (row * size) + 1;
    }
    if (row == 0) {
      return column + 1;
    }
    return (row * size) + (column + 1);
  }

  public boolean percolates() {
    return ufTree.connected(topNode, bottomNode);
  }
}
