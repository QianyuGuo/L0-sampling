
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DynamicL0Sampler implements Sampler<Integer> {
	// you may need to add additional class attributes

	// size of universe
	private int n;
	Hash h;
	private int sparsity;
	private int j;
	InsertL0Sampler inL0;
	private int a = 99999;
	private int b;
	// struct of sparse rec. structures
	private SSparseRec[] recovery;

	// selected from k-wise independent family of hash functions with k>=s/2
	private Hash hash;

	public DynamicL0Sampler(int n) {
		this.n = n;
		inL0 = new InsertL0Sampler(n);
		hash = new Hash(n * n);

		j = (int) ((int) (Math.log((double) n) / Math.log((double) 2)));
		recovery = new SSparseRec[j];
		for (int a = 0; a < j; a++) {
			recovery[a] = new SSparseRec(15, 60);// based on 12 ln(1/delta)
		}
	}

	public void add(Integer index, int value) {
		for (int i = 0; i < j; i++) {
			if ((n * n * n) * (Math.pow(2, -j)) >= hash.hash(index)) {
				recovery[i].add(index, value);
			} else {
				recovery[i].add(index, value);
			}
		}
	}

	public Integer output() {
		for (int i = 0; i < j; i++) {
			if (recovery[i].isSSparse()) {
				Collection keys = recovery[i].recover().keySet();
				for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
					Object key = iterator.next();
					int in = (int) key;
					if (hash.hash(in) < a) {
						a = hash.hash(in);
						b = in;
					}
				}
			}
		}
		return b;

	}

	// more difficult: return a vector index and its count-value
	public Pair<Integer, Integer> outputVector() {
		// you do not have to fill in
		return null;
	}

	// this may help
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("L0_samp: s-sparse strucs: %n");
		for (int i = 0; i < recovery.length; i++) {
			sb.append("struc: " + i + " " + recovery[i] + "%n");
		}
		return sb.toString();
	}

}
