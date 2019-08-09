
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SSparseRec {
    private int r, s;
    // selected from pairwise independent family of hash functions
    private Hash[] hashFamily;
    private OneSparseRec[][] net;
    int a,b,c;
    //record total 1-sparse number, more item number and zero item number in s-sparse
    int more=0;
    int one = 0;
    int zero = 0;
    String result = new String();
    HashMap<Integer,Integer>  hm = new HashMap<Integer,Integer>();
    
    public SSparseRec(int r, int sparsity){
        this.r = r;
        this.s = sparsity;
        hashFamily = new Hash[r];
    	net = new OneSparseRec[r][2*s];
        initialise();
    }

    private void initialise() {
    	for (int i = 0; i<r;i++) {
    		for(int j = 0;j<2*s;j++) {
    			net[i][j] = new OneSparseRec();
    		}
    	}
    	for(int x=0;x<r;x++) {
    		hashFamily[x] = new Hash(2*s);
    	}
			}   
    //add to each hash function and 1-sparseRec
    public void add(int index, int value) {
    	for (int i= 0;i<r;i++) {		
    		int a  = hashFamily[i].hash(index);
    		net[i][a].add(index,value);
    	}
    	}
    //check if it is s-sparse and if it is s-sparse then put update value into hash map
    public boolean isSSparse() {
    	for(int i = 0;i<r;i++) {
    		//record 1-sparse number, more items number, zero items number for each copy
    		a=0;
    		b=0;
    		c=0;
			for (int j = 0;j<2*s;j++) {
				if(net[i][j].isOneSparse()) {
					a+=1;
					one+=1;
				}else if(net[i][j].toString().equals("more")) {
					b+=1;
					more+=1;
				}else if(net[i][j].toString().equals("zero")) {
					c+=1;
					zero+=1;
				}
				}	
			if(a>s) {
				return false;
			}
			if(a+b>s) {
				return false;
			}
			if(a<=s & a>0 & b==0 ) {
				for(int x=0;x<2*s;x++) {
					if(!net[i][x].toString().equals("zero")){
						String str = net[i][x].toString();
						String[] s = new String[2];
						s = str.split(" ");
						int in = Integer.parseInt(s[0]);
						int v = Integer.parseInt(s[1]);
						if(hm.containsKey(in)) {
						int n =	hm.get(in);
						hm.put(in, n+v);
						}else {
							hm.put(in, v);
						}						
					}		
				}			
    			return true;
    		}
    	}
        return false;
			}
    	
    //	return test result of s-sparse
    public String sparseRecTest() {
    	if (isSSparse()) {
    		Iterator iter = hm.entrySet().iterator();
    		while (iter.hasNext()) {
    			Map.Entry entry = (Map.Entry) iter.next();
    			Object key = entry.getKey();
    			Object value = entry.getValue();
    			result+=(key + " " + value+"\n");
    		}
    		return result;
    	}else {		
    		if(more!=0) {
    		return "more";}
    		if (one!=0) {
    			return "more";
    		}
    	}
    	return "zero";
    		}
		
    

    public HashMap<Integer,Integer> recover() {
    	return hm;
    }

    // this might help
    @Override public String toString() {
        StringBuilder sb = new StringBuilder("SSparseRecoveryEstimator{array=[");
        int numCols = 2*s;

        for (int i=0; i<r; i++)
            for (int j=0; j<numCols; j++)
                sb.append(String.format("(%d,%d)=%s, ", i, j, net[i][j].toString()));
        sb.append("]}");

        return sb.toString();
    }




}
