import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class currAct {
    Scanner sc = new Scanner(System.in);
    private double deposit, withdraw;
    private final double minimumBalance = 5000.00, maximumBalance = 10000000f;

    FileWriter fw;

    {
        try {
            fw = new FileWriter("banking.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void display(String name, long accNum, long number, double balance) {
        try {
            System.out.println("Name of holder: " + name);
            System.out.println("Mobile Number: " + number);
            System.out.println("Account number: " + accNum);
            System.out.println("Type of account: Current Account");
            System.out.printf("Minimum Balance can be: ₹%.2f\n", minimumBalance);
            System.out.printf("Maximum Balance can be: ₹%.2f\n", maximumBalance);
            fw.write("Name of holder: " + name + "\nMobile Number: " + number + "\nBalance in the account: ₹" + balance + "\n");
            fw.flush();
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void display2(double balance) {
        try {
            System.out.printf("Balance in the account: ₹%.2f\n", balance);
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void getDeposit() {
        try {
            System.out.printf("Amount deposited: ₹%.2f\n", deposit);
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void setDeposit(double deposit1) {
        this.deposit = deposit1;
    }

    void getWithdraw() {
        try {
            System.out.printf("Amount withdrew: ₹%.2f\n", withdraw);
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void setWithdraw(double withdraw1) {
        this.withdraw = withdraw1;
    }

    void currentDeposit(long number, String name, double balance) {
        try {
            if (balance <= maximumBalance) {
                System.out.printf("New balance in the account: ₹%.2f\n", balance);
                System.out.println("Details has been sent on your mobile number: " + number);
                fw.write("Name of holder: " + name + "\nMobile Number: " + number + "\nNew balance in the account: ₹" + balance + "\nAmount Deposited: ₹" + deposit + "\n");
                fw.flush();
            } else {
                System.out.printf("You can't deposit ₹%.2f because it exceed the maximum limit of: ₹%.2f\n", deposit, maximumBalance);
                System.out.println("Details has been sent on your mobile number: " + number);
                System.out.println("\n********** Thank You **********");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void currentWithdraw(long number, String name, double balance) {
        try {
            if (balance > 0) {
                System.out.printf("New balance in the account: ₹%.2f\n", balance);
                System.out.println("Details has been sent on your mobile number: " + number);
                fw.write("Name of holder: " + name + "\nMobile Number: " + number + "\nNew balance in the account: ₹" + balance + "\nAmount Withdraw: ₹" + withdraw + "\n");
                fw.flush();
            } else if (balance < 0) {
                System.out.printf("You can't withdraw ₹%.2f more amount than your current balance: ₹%.2f\n", -balance, (balance + withdraw));
                System.out.println("Details has been sent on your mobile number: " + number);
                System.out.println("\n********** Thank You **********");
                System.exit(0);
            } else {
                System.out.printf("New balance amount in the account: ₹%.2f\n", balance);
                System.out.print("Are you going to close you account (Yes/No): ");
                String ans = sc.next();
                if (ans.equalsIgnoreCase("Yes")) {
                    System.out.println("Please contact bank for further details and procedure.");
                } else if (ans.equalsIgnoreCase("No")) {
                    System.out.printf("Service charge will be imposed as your new balance: ₹%.2f, is less than minimum balance: ₹%.2f\n", balance, minimumBalance);
                    System.out.println("So, your balance will deducted 2% (two percent) every month.");
                } else {
                    System.out.println("You have given a wrong input.");
                }
                System.out.println("Details has been sent on your mobile number: " + number);
                System.out.println("\n********** Thank You **********");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void currentPenalty(long number, double balance) {
        try {
            if (balance < minimumBalance) {
                System.out.printf("Service charge will be imposed as your new balance: ₹%.2f, is less than minimum balance: ₹%.2f\n", balance, minimumBalance);
                double newBalance = (balance * 0.02);
                System.out.println("So, your balance will deducted 2% (two percent) every month.");
                System.out.printf("And, your new balance will become after one month: ₹%.2f\n", newBalance);
                System.out.println("Details has been sent on your mobile number: " + number);
            }
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void currentTransfer(long number, String hName, double balance, double amount, String rName) {
        try {
            System.out.print("Enter the mobile number of the receiver: ");
            long mRNumber = sc.nextLong();
            System.out.print("Enter the account number of the receiver: ");
            long accountNum = sc.nextLong();
            System.out.print("Re-Enter the account number of the receiver to verify: ");
            long accountNumVerify = sc.nextLong();
            if (accountNum == accountNumVerify) {
                if (amount < balance) {
                    System.out.printf("Transferring the ₹%.2f to Mr/s,%s , account number: %d\n", amount, rName, accountNum);
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Transferred");
                } else if (amount > balance) {
                    System.out.printf("You can't transfer ₹%.2f as you have only ₹%.2f in your account balance.\n", amount, balance);
                } else {
                    System.out.printf("You can't transfer the whole balance of your account, i.e., ₹%.2f as you have only ₹%.2f in your account balance.\n", amount, balance);
                }
                fw.write("Name of the receiver: " + rName + "\nMobile Number of the receiver: " + mRNumber + "\n");
                fw.write("Name of the holder: " + hName + "\nMobile Number of the holder: " + number + "\nNew balance in the account: ₹" + balance + "\nAmount Transferred: ₹" + amount + "\n");
                fw.flush();
            } else {
                System.out.println("You both given account does not match.");
                System.out.println(accountNum + " and are different " + accountNumVerify);
            }
            System.out.println("Details has been sent on your mobile number: " + number);
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }
}

class savAcct {
    Scanner sc = new Scanner(System.in);
    private double deposit,withdraw;
    private final double minimumBalance = 3000,  maximumBalance = 1000000;

    FileWriter fw;

    {
        try {
            fw = new FileWriter("banking.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void display(String name, long accNum, long number, double balance) {
        try {
            System.out.println("Name of holder: " + name);
            System.out.println("Mobile Number: " + number);
            System.out.println("Account number: " + accNum);
            System.out.println("Type of account: Saving Account");
            System.out.printf("Minimum Balance can be: ₹%.2f\n", minimumBalance);
            System.out.printf("Maximum Balance can be: ₹%.2f\n", maximumBalance);
            fw.write("Name of holder: " + name + "\nMobile Number: " + number + "\nBalance in the account: ₹" + balance + "\n");
            fw.flush();
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void display2(double balance) {
        try {
            System.out.printf("Balance in the account: ₹%.2f\n", balance);
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void getDeposit() {
        try {
            System.out.printf("Amount deposited: ₹%.2f\n", deposit);
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void setDeposit(double deposit1) {
        this.deposit = deposit1;
    }

    void getWithdraw() {
        try {
            System.out.printf("Amount withdrew: ₹%.2f\n", withdraw);
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void setWithdraw(double withdraw1) {
        this.withdraw = withdraw1;
    }

    void savingDeposit(long number, String name, double balance) {
        try {
            if (balance <= maximumBalance) {
                System.out.printf("New balance amount in the account: ₹%.2f\n", balance);
                System.out.println("Details has been sent on your mobile number: " + number);
                fw.write("Name of holder: " + name + "\nMobile Number: " + number + "\nNew balance in the account: ₹" + balance + "\nAmount Deposited: ₹" + deposit + "\n");
                fw.flush();
            } else {
                System.out.printf("You can't deposit ₹%.2f because it exceed the maximum limit of: ₹%.2f\n", deposit, maximumBalance);
                System.out.println("Details has been sent on your mobile number: " + number);
                System.out.println("\n********** Thank You **********");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void savingWithdraw(long number, String name, double balance) {
        try {
            if (balance > 0) {
                System.out.printf("New balance amount in the account: ₹%.2f\n", balance);
                System.out.println("Details has been sent on your mobile number: " + number);
                fw.write("Name of holder: " + name + "\nMobile Number: " + number + "\nNew balance in the account: ₹" + balance + "\nAmount Withdraw: ₹" + withdraw + "\n");
                fw.flush();
            } else if (balance < 0) {
                System.out.printf("You can't withdraw ₹%.2f more amount than your current balance: ₹%.2f\n", -balance, (balance + withdraw));
                System.out.println("Details has been sent on your mobile number: " + number);
                System.out.println("\n********** Thank You **********");
                System.exit(0);
            } else {
                System.out.printf("New balance amount in the account: ₹%.2f\n", balance);
                System.out.print("Are you going to close you account (Yes/No): ");
                String ans = sc.next();
                if (ans.equalsIgnoreCase("Yes")) {
                    System.out.println("Please contact bank for further details and procedure.");
                } else if (ans.equalsIgnoreCase("No")) {
                    System.out.printf("Service charge will be imposed as your new balance: ₹%.2f, is less than minimum balance: ₹%.2f\n", balance, minimumBalance);
                    System.out.println("So, your balance will deducted 1% (one percent) every month.");
                } else {
                    System.out.println("You have given a wrong input.");
                }
                System.out.println("Details has been sent on your mobile number: " + number);
                System.out.println("\n********** Thank You **********");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void savingPenalty(long number, double balance) {
        try {
            if (balance < minimumBalance) {
                System.out.printf("Service charge will be imposed as your new balance: ₹%.2f, is less than minimum balance: ₹%.2f\n", balance, minimumBalance);
                double newBalance = (balance * 0.01);
                System.out.println("So, your balance will deducted 1% (one percent) every month.");
                System.out.printf("And, your new balance will become after one month: ₹%.2f\n", newBalance);
                System.out.println("Details has been sent on your mobile number: " + number);
            }
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

    void savingTransfer(long number, String hName, double balance, double amount, String rName) {
        try {
            System.out.print("Enter the mobile number of the receiver: ");
            long mRNumber = sc.nextLong();
            System.out.print("Enter the account number of the receiver: ");
            long accountNum = sc.nextLong();
            System.out.print("Re-Enter the account number of the receiver to verify: ");
            long accountNumVerify = sc.nextLong();
            if (accountNum == accountNumVerify) {
                if (amount < balance) {
                    System.out.printf("Transferring the ₹%.2f to Mr/s,%s , account number: %d\n", amount, rName, accountNum);
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Transferred");
                } else if (amount > balance) {
                    System.out.printf("You can't transfer ₹%.2f as you have only ₹%.2f in your account balance.\n", amount, balance);
                } else {
                    System.out.printf("You can't transfer the whole balance of your account, i.e., ₹%.2f as you have only ₹%.2f in your account balance.\n", amount, balance);
                }
                fw.write("Name of the receiver: " + rName + "\nMobile Number of the receiver: " + mRNumber + "\n");
                fw.write("Name of the holder: " + hName + "\nMobile Number of the holder: " + number + "\nNew balance in the account: ₹" + balance + "\nAmount Transferred: ₹" + amount + "\n");
                fw.flush();
            } else {
                System.out.println("You both given account does not match.");
                System.out.println(accountNum + " and are different " + accountNumVerify);
            }
            System.out.println("Details has been sent on your mobile number: " + number);
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        }
    }

}

public class Bank_Management {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("********** Online Bank **********");
            System.out.print("Enter your Name: ");
            String name = sc.nextLine();
            String[] words = name.split("\\s");
            StringBuilder capitalizeStr = new StringBuilder();
            for (String word : words) {
                String firstLetter = word.substring(0, 1);
                String remainingLetters = word.substring(1);
                capitalizeStr.append(firstLetter.toUpperCase()).append(remainingLetters).append(" ");
            }
            String newName = capitalizeStr.toString();
            System.out.print("Enter your mobile number: ");
            long number = sc.nextLong();
            System.out.print("Enter your Account Number: ");
            long accNum = sc.nextLong();
            int max = 99;
            int min = 10;
            int otp1 = min + (int) (Math.random() * ((max - min) + 1));
            int otp2 = min + (int) (Math.random() * ((max - min) + 1));
            int otp3 = min + (int) (Math.random() * ((max - min) + 1));
            String otp = String.valueOf(otp1) + otp2 + otp3;
            System.out.println("OTP has been sent on the mobile number: " + number);
            System.out.println("OTP generated: " + otp);
            System.out.print("Enter the OTP: ");
            String OTP = sc.next();
            System.out.println("Verifying the OTP: " + OTP + ".....");
            Calendar cal = Calendar.getInstance();
            TimeUnit.SECONDS.sleep(1);
            if (OTP.equals(otp)) {
                System.out.println("Logged in --> " + cal.getTime() + " as " + newName);
                sc.nextLine();
                System.out.print("Enter your account type,\nFor 'Current Account' enter 'C' or 'Saving account' enter 'S': ");
                String type = sc.nextLine();
                System.out.println("Option entered: " + type.toUpperCase());
                if (type.equalsIgnoreCase("C")) {
                    currAct c = new currAct();
                    double max1 = 10000000f;
                    double min1 = 10000f;
                    double balance = min1 + (long) (Math.random() * ((max1 - min1) + 1));
                    c.display(newName, accNum, number, balance);
                    while (true) {
                        c.display2(balance);
                        System.out.println("Enter:\n 'D' for depositing \n 'W' for withdrawing money \n 'T' for transfer of money \n 'E' for exiting ");
                        System.out.print("Enter your option: ");
                        String wd = sc.nextLine();
                        System.out.println("Option entered: " + wd.toUpperCase());
                        if (wd.equalsIgnoreCase("D")) {
                            System.out.print("Enter the amount to deposit: ₹");
                            double deposit = sc.nextDouble();
                            sc.nextLine();
                            balance = balance + deposit;
                            c.setDeposit(deposit);
                            c.getDeposit();
                            c.currentDeposit(number, newName, balance);
                            c.currentPenalty(number, balance);
                        } else if (wd.equalsIgnoreCase("W")) {
                            System.out.print("Enter the amount to withdraw: ₹");
                            double withdraw = sc.nextDouble();
                            sc.nextLine();
                            balance = balance - withdraw;
                            c.setWithdraw(withdraw);
                            c.getWithdraw();
                            c.currentWithdraw(number, newName, balance);
                            c.currentPenalty(number, balance);
                        } else if (wd.equalsIgnoreCase("T")) {
                            System.out.print("Enter the amount to be sent to the receiver: ");
                            double amount = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Enter the name of the receiver: ");
                            String rName = sc.nextLine();
                            String[] words1 = rName.split("\\s");
                            StringBuilder capitalizeStr1 = new StringBuilder();
                            for (String word11 : words1) {
                                String firstLetter = word11.substring(0, 1);
                                String remainingLetters = word11.substring(1);
                                capitalizeStr1.append(firstLetter.toUpperCase()).append(remainingLetters).append(" ");
                            }
                            String rNewName = capitalizeStr1.toString();
                            balance = balance - amount;
                            c.currentTransfer(number, newName, balance, amount, rNewName);
                        } else if (wd.equalsIgnoreCase("E")) {
                            System.out.println("Details has been sent on your mobile number: " + number);
                            break;
                        } else {
                            System.out.println("Error");
                            break;
                        }
                    }
                } else if (type.equalsIgnoreCase("S")) {
                    savAcct s = new savAcct();
                    double max1 = 1000000f;
                    double min1 = 5000f;
                    double balance = min1 + (long) (Math.random() * ((max1 - min1) + 1));
                    s.display(newName, accNum, number, balance);
                    while (true) {
                        s.display2(balance);
                        System.out.println("Enter:\n 'D' for depositing \n 'W' for withdrawing money \n 'T' for transfer of money \n 'E' for exiting ");
                        System.out.print("Enter your option: ");
                        String wd = sc.nextLine();
                        System.out.println("Option entered: " + wd.toUpperCase());
                        if (wd.equalsIgnoreCase("D")) {
                            System.out.print("Enter the amount to deposit: ₹");
                            double deposit = sc.nextDouble();
                            sc.nextLine();
                            balance = balance + deposit;
                            s.setDeposit(deposit);
                            s.getDeposit();
                            s.savingDeposit(number, newName, balance);
                            s.savingPenalty(number, balance);
                        } else if (wd.equalsIgnoreCase("W")) {
                            System.out.print("Enter the amount to withdraw: ₹");
                            double withdraw = sc.nextDouble();
                            sc.nextLine();
                            balance = balance - withdraw;
                            s.setWithdraw(withdraw);
                            s.getWithdraw();
                            s.savingWithdraw(number, newName, balance);
                            s.savingPenalty(number, balance);
                        } else if (wd.equalsIgnoreCase("T")) {
                            System.out.print("Enter the amount to be sent to the receiver: ");
                            double amount = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Enter the name of the receiver: ");
                            String rName = sc.nextLine();
                            String[] words1 = rName.split("\\s");
                            StringBuilder capitalizeStr1 = new StringBuilder();
                            for (String word11 : words1) {
                                String firstLetter = word11.substring(0, 1);
                                String remainingLetters = word11.substring(1);
                                capitalizeStr1.append(firstLetter.toUpperCase()).append(remainingLetters).append(" ");
                            }
                            String rNewName = capitalizeStr1.toString();
                            balance = balance - amount;
                            s.savingTransfer(number, newName, balance, amount, rNewName);
                        } else if (wd.equalsIgnoreCase("E")) {
                            System.out.println("Details has been sent on your mobile number: " + number);
                            break;
                        } else {
                            System.out.println("Error");
                            break;
                        }
                    }
                } else {
                    System.out.println("Error");
                }
            } else {
                System.out.println("Incorrect OTP");
            }
            System.out.println("Details has been saved in banking.txt");
        } catch (InputMismatchException ex) {
            System.out.println("You have given a wrong input.\nError --> " + ex);
        } catch (Exception e) {
            System.out.println("Error --> " + e);
        } finally {
            System.out.println("\n********** Thank You **********");
        }
    }
}
