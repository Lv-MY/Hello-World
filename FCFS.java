package test_lmy;

public class FCFS extends parameter{
	  FCFS []fcfs;
	  int n;
	  String []priority;
	  
	  
      public FCFS(String Thread_Name, int Arrive_Time, int Server_Time){
    	  super(Thread_Name, Arrive_Time,  Server_Time);
      }
      public FCFS(int n){
    	  this.n = n;
      }
      public FCFS(){}
      
      
      private void setNum(String a[][])//将GUI中数据转换为Java类中数据
      {
 
    	  int b[][]  = new int[n][3];
    	  for (int i = 0; i <n; i++) {
				for (int j = 1; j < 3; j++) {
					 b[i][j] =  Integer.parseInt(a[i][j]);
				}
			}
    	  FCFS temp[] = new FCFS[n];
    	  for (int i = 0; i <n; i++) {
					temp[i] = new FCFS(a[i][0],b[i][1], b[i][2]);
			}
    	  fcfs = temp;
      }

      
      private void Arrive_Sort()//按到达时间进行排序
      {
    	  FCFS f = new FCFS();
    	  for(int i = 1; i<n; i++){
    		  for(int j = 0; j<n-i; j++){
    			  if(fcfs[j].Arrive_Time>fcfs[j+1].Arrive_Time){
    				  f = fcfs[j];
    				  fcfs[j] = fcfs[j+1];
    				  fcfs[j+1] = f;
    			  }
    		  }
    	  }
      }
      
      private void Priority()//存在疑问
      {
    	  priority = new  String[n];
    	  for(int i = 0; i<n; i++){
    		  priority[i] = fcfs[i].Thread_Name;
    	  }
      }
      
      
      private void FCFS_Func(String a[][])//功能性方法
      {
    	  setNum(a);
    	  Arrive_Sort();
    	  Priority();
    	  fcfs[0].Over_Time = fcfs[0].Arrive_Time + fcfs[0].Server_Time;
    	  fcfs[0].TurnaRound_Time = fcfs[0].Over_Time - fcfs[0].Arrive_Time;
    	  fcfs[0].Begin_Time = fcfs[0].Arrive_Time;
    	  fcfs[0].WeightTurn_Time = 1.0*fcfs[0].TurnaRound_Time/fcfs[0].Server_Time;
    	  for(int i = 1; i<n; i++){
    		  if(fcfs[i].Arrive_Time<fcfs[i-1].Over_Time){
    			  fcfs[i].Over_Time = fcfs[i-1].Over_Time + fcfs[i].Server_Time;
    			  fcfs[i].Begin_Time = fcfs[i-1].Over_Time;
    			  fcfs[i].TurnaRound_Time = fcfs[i].Over_Time - fcfs[i].Arrive_Time;
    			  fcfs[i].WeightTurn_Time = 1.0*fcfs[i].TurnaRound_Time/fcfs[i].Server_Time;
    			  
    		  }
    		  else{
    			  fcfs[i].Over_Time = fcfs[i].Arrive_Time + fcfs[i].Server_Time;
    	    	  fcfs[i].TurnaRound_Time = fcfs[i].Over_Time - fcfs[i].Arrive_Time;
    	    	  fcfs[i].Begin_Time = fcfs[i].Arrive_Time;
    	    	  fcfs[i].WeightTurn_Time = 1.0*fcfs[i].TurnaRound_Time/fcfs[i].Server_Time;
    		  }
    	  }
    	  
      }
       public String[][] outCome(String a[][])
       {
    	   FCFS_Func(a);
    	   String [][]q = new String[n][8];
    	   for(int i = 0; i<n; i++){
    		   q[i][0] = fcfs[i].Thread_Name;
    		   q[i][1] = String.valueOf(fcfs[i].Arrive_Time);
    		   q[i][2] = String.valueOf(fcfs[i].Server_Time);
    		   q[i][3] = String.valueOf(fcfs[i].Begin_Time);
    		   q[i][4] = String.valueOf(fcfs[i].Over_Time);
    		   q[i][5] = String.valueOf(fcfs[i].TurnaRound_Time);
    		   q[i][6] = String.valueOf(fcfs[i].WeightTurn_Time);
    		   q[i][7] = priority[i];       //为优先权顺序，即对应的数组名
    	   }
    	   return q;
    	   
       }
      
//    public void  play(String a[][])//测试函数
//    {
//   	 String [][]q = outCome(a);
//   	 for(int i = 0; i<n; i++){
//   		 System.out.println(q[i][0]);
//   	 }
//    }
     
}
