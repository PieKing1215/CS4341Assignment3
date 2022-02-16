package astar;

import java.util.OptionalInt;

public class Board {
    int[][] nums;

    public Board(int[][] nums) {
        this.nums = nums;
    }

    public Board(Board copy) {
        this(copy.cloneNums());
    }

    int[][] cloneNums() {
        int[][] newNums = new int[this.nums.length][];
        for (int i = 0; i < newNums.length; i++) {
            newNums[i] = this.nums[i].clone();
        }
        return newNums;
    }

    public int width() {
        return nums[0].length;
    }

    public int height() {
        return nums.length;
    }

    public int get(int x, int y) {
        return nums[y][x];
    }

    public int get(Position pos) {
        return get(pos.x, pos.y);
    }

    public void set(int x, int y, int v) {
        nums[y][x] = v;
    }

    public void set(Position pos, int v) {
        set(pos.x, pos.y, v);
    }

    public Position find(int v) {
        for (int y = 0; y < this.height(); y++) {
            for (int x = 0; x < this.width(); x++) {
                if (get(x, y) == v) {
                    return new Position(x, y);
                }
            }
        }
        return new Position(-1, -1);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int y = 0; y < height(); y++) {
            ret.append("\n");
            for (int x = 0; x < width(); x++) {
                ret.append(nums[y][x]).append(" ");
            }
        }
        return ret.toString();
    }

    public OptionalInt[] squaresAround(Position pos) {
        // 0 1 2
        // 3   4
        // 5 6 7
        OptionalInt[] vals = new OptionalInt[8];
        int i = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    int nx = pos.x + dx;
                    int ny = pos.y + dy;
                    if (ny >= 0 && ny < height()
                            && nx >= 0 && nx < width()) {
                        vals[i] = OptionalInt.of(get(nx, ny));
                    } else {
                        vals[i] = OptionalInt.empty();
                    }
                    i++;
                }
            }
        }

        return vals;
    }
}
