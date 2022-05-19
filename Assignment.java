import java.util.HashMap;
import java.util.Scanner;

public class Assignment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String addCarNum; // 입력받는 차량 번호
        int setBusPrice; // 설정할 버스 요금
        String selectedCarNum; // 행동하는 차량 번호
        Car selectedCar; // 행동하는 차 객체
        HashMap<String, Car> carList = new HashMap<>(); // 차량 번호, 객체 저장
        int doWhat; // 입력받는 사용자 행동

        // 같은 번호의 버스를 생성시키면 삭제를 어떻게 해야 할지?? 입력받을때 객체 생성 안하면 되긴 하는데 궁금함. bus2=null 하면 되는건지?? 가비지에 들어간다는데 더 좋은 방법은 없는지??


        loop:
        while (true) {
            System.out.println("할 행동을 선택하세요. 1(차량 생성), 2(차량 정보), 3(차량 행동), 4(종료) 숫자만 입력하시면 됩니다.");
            doWhat = scanner.nextInt();


            //차량 생성
            if(doWhat==1){
                System.out.println("차량 생성");
                System.out.println("생성할 차량 종류를 선택하세요. 1(버스), 2(택시), 3(이전단계로) 숫자만 입력하시면 됩니다.");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("버스 객체를 생성합니다. 생성할 버스의 차량 번호를 입력해 주세요.");
                        addCarNum = scanner.nextInt()+"";
                        if (Car.addCar( addCarNum+ "")) {
                            System.out.printf("%s 버스의 요금을 입력해 주세요.\n",addCarNum);
                            setBusPrice = scanner.nextInt();
                            carList.put(addCarNum, new Bus(addCarNum,setBusPrice));
                        }else{
                            System.out.println("생성 실패. 이미 존재하는 차량 번호 입니다.");
                        }
                        break;
                    case 2:
                        System.out.println("택시 객체를 생성합니다. 생성할 택시의 차량 번호를 입력해 주세요.");
                        addCarNum = scanner.nextInt()+"";
                        if (Car.addCar( addCarNum+ "")) {
                            carList.put(addCarNum, new Taxi(addCarNum));
                        }else{
                            System.out.println("생성 실패. 이미 존재하는 차량 번호 입니다.");
                        }
                        break;
                    case 3:
                        continue loop;
                    default:
                        System.out.println("잘못 입력 하셨습니다. 숫자만 입력해 주세요");
                        continue loop;
                }
            }

            //차량 정보
            if(doWhat==2){
                System.out.println("차량 정보");
                for (String key : carList.keySet()) {
                    selectedCar = carList.get(key);
                    selectedCar.printCarInfo();
                    System.out.println();
                }
            }

            //차량 행동
            if (doWhat == 3) {
                System.out.println("차량 행동");
                System.out.println("행동할 차량 번호를 입력하세요.");

                //carList 에 저장된 Car 객체를 차량번호 key값으로 불러옴
                selectedCarNum = scanner.next();
                selectedCar = carList.get(selectedCarNum);
                if (selectedCar == null) {
                    System.out.printf("%s번 차량은 존재하지 않습니다.\n",selectedCarNum);
                    continue loop;
                }

                selectedCar.printCarInfo();
                System.out.println();
                System.out.println("할 차량 행동을 선택하세요. 1(속도 변경), 2(승객 탑승), 3(승객 하차), 4(운행 시작), 5(운행 정지), 6(이전단계로) 숫자만 입력하시면 됩니다.");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("변경할 만큼의 속도를 입력해 주세요. 가속은 +, 감속은 -");
                        selectedCar.changeSpeed(scanner.nextInt());

                        break;
                    case 2:
                        if (selectedCar instanceof Bus) {
                            System.out.println("탑승할 승객수를 입력해 주세요.");
                        } else if (selectedCar instanceof Taxi) {

                            System.out.println("이동할 거리를 입력해 주세요");
                        }
                        selectedCar.addPassenger(scanner.nextInt());
                        break;
                    case 3:
                        if (selectedCar instanceof Bus) {
                            System.out.println("하차할 승객수를 입력해 주세요.");
                            selectedCar.removePassenger(scanner.nextInt());
                        } else if (selectedCar instanceof Taxi) {
                            selectedCar.removePassenger();
                        }
                        break;
                    case 4:
                        selectedCar.startCar();
                        break;
                    case 5:
                        selectedCar.stopCar();
                        break;
                    case 6:
                        continue loop;
                    default:
                        System.out.println("잘못 입력 하셨습니다. 숫자만 입력해 주세요");
                        continue loop;
                }
            }
            if (doWhat == 4) {
                return;
            }

        }
    }
}
