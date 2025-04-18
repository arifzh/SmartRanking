import java.util.Arrays;

class Player {
  String name;
  int score;

  Player(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String toString() {
    return name + " (Score: " + score + ")";
  }
}

class MaxHeap {
  private Player[] heap;
  private int size;
  private static final int CAPACITY = 100;

  MaxHeap() {
    heap = new Player[CAPACITY];
    size = 0;
  }

  public void insert(Player player) {
    if (size >= CAPACITY) {
      throw new RuntimeException("Heap is full");
    }
    heap[size] = player;
    heapifyUp(size);
    size++;
  }

  public Player extractMax() {
    if (size == 0)
      return null;

    Player max = heap[0];
    heap[0] = heap[--size];
    heapifyDown(0);
    return max;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void heapifyUp(int index) {
    while (index > 0 && heap[parent(index)].score < heap[index].score) {
      swap(index, parent(index));
      index = parent(index);
    }
  }

  private void heapifyDown(int index) {
    int largest = index;
    int left = left(index);
    int right = right(index);

    if (left < size && heap[left].score > heap[largest].score) {
      largest = left;
    }

    if (right < size && heap[right].score > heap[largest].score) {
      largest = right;
    }

    if (largest != index) {
      swap(index, largest);
      heapifyDown(largest);
    }
  }

  private void swap(int i, int j) {
    Player temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

  private int parent(int i) {
    return (i - 1) / 2;
  }

  private int left(int i) {
    return 2 * i + 1;
  }

  private int right(int i) {
    return 2 * i + 2;
  }
}

public class TournamentRankingSystem {
  public static void main(String[] args) {
    MaxHeap rankingHeap = new MaxHeap();

    // Sample players
    rankingHeap.insert(new Player("Alice", 85));
    rankingHeap.insert(new Player("Bob", 95));
    rankingHeap.insert(new Player("Charlie", 78));
    rankingHeap.insert(new Player("Diana", 92));
    rankingHeap.insert(new Player("Ethan", 88));

    System.out.println("🏆 Tournament Rankings:");
    int rank = 1;
    while (!rankingHeap.isEmpty()) {
      Player top = rankingHeap.extractMax();
      System.out.println(rank + ". " + top);
      rank++;
    }
  }
}
