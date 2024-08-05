fun main(){
  for(i in 1..20){
    if(i==13){
      continue
    }else if(i==18){
      break;
    }else{
      println(i)
    }
  }
}