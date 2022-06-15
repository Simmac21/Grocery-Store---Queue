import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GroceryStore {


    public static void main(String[] args) throws InterruptedException {

        try {
            String fileName = "CustomerData_Example.txt"; //File name
            File f1 = new File(fileName); //creating file object and passing file name
            Scanner input = new Scanner(f1);//creating scanner to read through file
            int[] NOI = new int[100]; //creating an array to add readed data (number of items)
            for(int i=0; input.hasNextInt(); i++){ //running a loop through an array
                NOI[i] = input.nextInt();
            }
            //creating an array of type customer of size given in file (saved in NOI array)
            Customer c[] = new Customer[NOI[3]];

            //creating instances of customers with the help of a loop
            int a = 4;
            while(a < NOI[3] + 4){
                Customer d = new Customer(NOI[a]);
                c[a - 4] = d;
                a++;
            }
            //creating an array of type LinkedQueue of size (fast lane queue +)
            LinkedQueue[] l = new LinkedQueue[NOI[0] + NOI[1]];

            //creating instances of LinkedQueue with the help of a loop
            int v=0;
            while(v != NOI[0]+NOI[1]){
                l[v] = new LinkedQueue();
                v++;
            }

            int in = 0; //index variable
            int ck = 0; //check variable
            for (int h = 0; h < c.length; h++) {//loop which runs until customer array ends
                if (c[h].getNI() <= NOI[2]) {//condition to check if customer NI is <= threshhold number (x)
                    //creating arrays to hold serving time of queues
                    int m1[] = new int[l.length];
                    int m2[] = new int[l.length];
                    //saving serving time of queues in array through loop
                    for (int u = 0; u < l.length; u++) {
                        m1[u] = l[u].servingTimeQueue();
                    }
                    int temp = 0;
                    //copying m1 to m2 so dont loose track after manupulating the array
                    System.arraycopy(m1, 0, m2, 0, m1.length);
                    if (h < c.length) {
                        for (int k = 0; k < m1.length; k++) {
                            //if we find the 0 sering time we update the variable to enqueue at that index
                            if (m1[k] == 0) {
                                in = k;
                                ck = 0;
                                break;//after updating breaking the loop to enqueue the customer to appropriate queue
                            } else {
                                ck = 1;
                            }
                        }
                    }

                    if (ck == 0) {

                    } else {
                        //doing array sorting to get the smallest nubmber at front
                        for (int ii = 0; ii < m1.length; ii++) {
                            for (int jj = ii + 1; jj < m1.length; jj++) {
                                if (m1[ii] == 0) {
                                    break;
                                } else if (m1[ii] > m1[jj]) {

                                    temp = m1[ii];
                                    m1[ii] = m1[jj];
                                    m1[jj] = temp;
                                }
                            }
                        }
                        int m3 = m1[0];//saving the smallest serving time (from sorted array) in m3
                        for (int g = 0; g < m1.length; g++) {
                            if (m2[g] == m3) {
                                m3 = m1[g];
                                in = g;//saving that index to in
                                ck = 1;
                                break;
                            }
                        }
                    }
                    l[in].enqueue(c[h]);//enqueueing the customer at index (in)
                } else {
                    //creating arrays to hold serving time of queues

                    //doing same as above but now for the customers whoes NI > threshhold point

                    int m1[] = new int[l.length - NOI[0]];
                    int m2[] = new int[l.length - NOI[0]];
                    for (int u = 0 + NOI[0]; u < l.length; u++) {

                        m1[u - NOI[0]] = l[u].servingTimeQueue();
                    }
                    System.arraycopy(m1, 0, m2, 0, m1.length);
                    if (h < c.length) {
                        for (int k = 0; k < m1.length; k++) {

                            if (m1[k] == 0) {
                                in = k + NOI[0];

                                ck = 0;
                                break;
                            } else {
                                ck = 1;
                            }
                        }
                    }
                    int temp = 0;
                    if (ck == 0) {
                    } else {
                        //sorting arrays
                        for (int ii = 0; ii < m1.length; ii++) {
                            for (int jj = ii + 1; jj < m1.length; jj++) {
                                if (m1[ii] > m1[jj]) {
                                    temp = m1[ii];
                                    m1[ii] = m1[jj];
                                    m1[jj] = temp;
                                }
                            }
                        }
                        int m3 = m1[0];//saving the smallest serving time (from sorted array) in m3
                        for (int g = 0; g < m2.length; g++) {
                            if (m2[g] == m3) {
                                in = g + NOI[0];
                                ck = 1;
                                break;

                            }
                        }
                    }
                    l[in].enqueue(c[h]);//enqueueing the customer at index (in)
                }

            }

            System.out.println("part A");
            //displaying the checkout lines with the help of loop
            int a1=0;
            while(a1 < NOI[0]){//running a loop till express lines (saved in aray (read from file))
                System.out.println("checkout(Express) #" + (a1+1) +
                        "(Est Time = " + l[a1].servingTimeQueue() +
                        " s)= " + l[a1].toString());
                a1++;
            }
            int a2= 0 + NOI[0];
            while(a2 < NOI[0] + NOI[1]){//running a loop till normal lines (saved in aray (read from file))
                System.out.println("checkout(normal ) #" + (a2+1) +
                        "(Est Time = " + l[a2].servingTimeQueue() +
                        " s)= " + l[a2].toString());
                a2++;
            }

            int ec = 0,nc1 = 0,nc2 = 0;//counters for express and normal lines

            //creating instances of LinkedQueue() of express and normal queues
            LinkedQueue express = new LinkedQueue();
            LinkedQueue normal1 = new LinkedQueue();
            LinkedQueue normal2 = new LinkedQueue();

            //running a loop in customer array
            for (Customer c1 : c) {
                //checking if customers number of items are greater than threshhold point (x)
                if (c1.getNI() > NOI[2]) {
                    if (nc1 <= nc2) {//comparing the normal1 and normal2 counters
                        normal1.enqueue(c1);//enqueing to normal1 queue if counter of nc1 is samll
                        nc1 += c1.servingTime();//adding serving time to the counter
                    } else {
                        normal2.enqueue(c1);//else enqueing to normal2 queue
                        nc2 += c1.servingTime();//adding to counter after enqueueing to nc2
                    }
                } else {//now comparing express and normal lines and enqueuing to shorter queue similarly
                    if (ec <= nc1 && ec <= nc2) {
                        express.enqueue(c1);
                        ec += c1.servingTime();
                    } else if (nc1 <= ec && nc1 <= nc2) {
                        normal1.enqueue(c1);
                        nc1 += c1.servingTime();
                    } else if (nc2 <= ec && nc2 <= nc1) {
                        normal2.enqueue(c1);
                        nc2 += c1.servingTime();
                    }
                }
            }

            //saving time to clear the store by comparing the counters
            int ct = 0;//clearance time
            if (ec > nc1 && ec > nc2) {
                ct = ec;
            } else if (nc1 > ec && nc1 > nc2) {
                ct = nc1;
            } else if (nc2 > ec && nc2 > nc1) {
                ct = nc2;
            }

            System.out.println("PART B - Nummber of customers in line after each minute (30s)");

            System.out.println("t(s)\tLine 1\tLine 2\tLine 3 ");

            int c1=0,c2=0,c3=0;//counters
            int q=0;//for while loop
            //displaying the number of customomers left in the queue after 30 seconds of time
            while(q<= (ct / 30)){
                if (!express.isEmpty() && q * 30 - c1 >= express.peek() * 5 + 45) {
                    c1 += express.peek() * 5 + 45;//updating counter to fulfill while condition
                    express.dequeue();//dequeing if the serving time is completed after 30sec
                }
                if (!normal1.isEmpty() && q * 30 - c2 >= normal1.peek() * 5 + 45) {
                    c2 += normal1.peek() * 5 + 45;//updating counter to fulfill while condition
                    normal1.dequeue();//dequeing if the serving time is completed after 30sec
                }
                if (!normal2.isEmpty() && q * 30 - c3 >= normal2.peek() * 5 + 45) {
                    c3 += normal2.peek() * 5 + 45;//updating counter to fulfill while condition
                    normal2.dequeue();//dequeing if the serving time is completed after 30sec
                }
                //displaying the condition of queue after 30 sec has passes
                System.out.println(q * 30 + "\t" + express.size() + "\t" + normal1.size() + "\t" + normal2.size());
                q++;
            }

            input.close();//closing scanner
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred.");//catching error
        }

    }

}