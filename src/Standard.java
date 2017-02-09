//standardList
public class Standard {
	private int type;
	private int count;
	private int num_intersect;
	private int num_block;
	private int point;
	
	public Standard(int type,int count,int num_intersect,int block,int point) {
		//-1不用管
		this.type=type;
		this.count=count;
		this.num_intersect=num_intersect;
		this.num_block=block;//-1不用管
		this.point=point;
	}
	
	public Standard getStandardByType(int type) {
		Standard stan = new Standard(1,5,1,-1,100);
		switch(type) {
			/*case 1:Standard stan = new Standard(1,5,1,-1,100);break;
			case 2:Standard stan = new Standard(1,4,1,100);break;
			case 3:Standard stan = new Standard(1,5,1,100);break;
			case 4:Standard stan = new Standard(1,5,1,100);break;
			case 5:Standard stan = new Standard(1,5,1,100);break;*/
			default:break;
		}
		return stan;
	}
}
