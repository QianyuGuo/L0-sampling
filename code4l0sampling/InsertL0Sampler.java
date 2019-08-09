
import java.util.HashMap;
//import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class InsertL0Sampler implements Sampler<Integer> {
    // you will need to add additional class attributes
    // size of universe
    private int n;
    // selected from k-wise independent family of hash functions with k>=s/2
    private Hash hash;
    int minHash = 9999;
    int result = 0;
    public InsertL0Sampler(int n) {
        this.n = n;
        hash = new Hash(n*n);
    }
    
    //find the minimum value of hash(i)
    public void add(Integer index, int value) {
    	if (hash.hash(index)<minHash) {
    		minHash = hash.hash(index);
    		result = index;
    	};
    }

    public Integer output() {
       return result;
    }

}
