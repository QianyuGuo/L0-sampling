
public class OneSparseRec {

    private int phi;
    private int iota;   
    private double a= 0;
    private double b = 0;
    private int p = 1111151;
    private int q = StdRandom.uniform(p);
    private double c = 0;
    public OneSparseRec() {
       
    }
    public void add(int index, int value) {
    	double i =  (long) Math.pow(q,index);
    	a = a+(value*i);
    	iota = iota+ index*value;
    	phi+=value;
    	
    }
    //according to formula to check if it is 1-sparse
    public boolean isOneSparse() {
    	c = a%p;
    	if(phi!=0) {
    		double j = (long) Math.pow(q,iota/phi);
    		b = (phi*j)%p;
    		if(b==c) {
        		return true;}
    	}
        return false;
    }
    public String oneSparseTest() {
        return toString();
    }
    // getters value
    public int getPhi() {
        return this.phi;
    }

    public int getIota() {
        return this.iota;
    }
    // this might help
    @Override public boolean equals(Object otherObj) {
        if (!(otherObj instanceof OneSparseRec)) return false;
        else {
            OneSparseRec oner = (OneSparseRec) otherObj;
            return this.getIota() == oner.getIota()
                    && this.getPhi() == oner.getPhi();
        }
    }
    @Override public String toString() {
        if(this.isOneSparse()) {
            return  iota/phi+" "+ phi;
        }else if(phi != 0){
        	return "more";
        }else if(iota !=0) {
        	return "more";
        }else if(a!=0) {
        	return "more";
        }
        else if(phi == 0&iota == 0) {
            return "zero";
        }else return"more";
    }


}
