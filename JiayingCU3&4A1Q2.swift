
var rightform: Bool = false
var nums:[Substring] = []
while(!rightform){
  // prompt user for user input
  print("Please type in the numbers seperated by \" \" ")
  // get the user input
  let input = readLine();
  
  // error handling in case of no input
  if input == ""{
    print("Please type in the right form!")
    continue

  }
  // seperate the nums by empty spaces    
  nums = input!.split(separator: " ")
  for i in nums{
    if(Int(i) == nil){
      print("Please type in the right form!")
      break

    }
    rightform = true;
  }
  


}
// create three list to hold everything
var positiveDouble: [String] = []
var positiveInt: [String] = []
var negative: [String] = []
for sub in nums{
  let i = String(sub);
  // check if it is negative
  if i.hasPrefix("-"){
    negative.append(i)
  // check if it has decimal
  }else if(i.contains(".")){
    positiveDouble.append(i)
  }else {
    positiveInt.append(i)

  }
}
// print out the result
print("Negative Number \(negative)");
print("Positive Integer \(positiveInt)");
print("Positive Double \(positiveDouble)");
      
    
  




