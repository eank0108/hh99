import java.util.ArrayList;

abstract class Car{
    int gas = 50; // 주유량
    long gasCheckTime; // 주유량 체크 시간
    int timePerGas = 1; // 연비, 1분당 소모되는 기름 양
    int speed =60; // 현재 속도
    String state; //Bus: "운행", "차고지 행" or Taxi: "운행", "일반"
    String car; //Bus or Taxi
    final String carNumber;
    static private ArrayList<String> carNumberArray  = new ArrayList<>();


    public Car(String carNumber) {
        carNumberArray.add(carNumber);
        this.gasCheckTime = System.currentTimeMillis();
        this.carNumber = carNumber;
        this.state = "운행"; // 요구사항 - 택시 객체 생성시 최초 상태는 ‘운행’ 상태가 됨
    }

    static boolean addCar(String carNumber) {
        // 차량 번호 중복 여부 검사 함수
        if (carNumberArray.contains(carNumber)) {
            return false;
        }
        return true;
    }
    void noGas() {
        System.out.print("기름이 부족합니다. ");
        this.stopCar();
    }
    abstract void stopCar();
    abstract void printCarInfo();

    abstract void removePassenger(int... count);
    abstract void addPassenger(int value);

    boolean checkOil(){
        setOil();
        if (this.gas < 10) {
            this.noGas();
            return false;
        }
        return true;
    }
    boolean checkState(){
        if (this.state !="운행") {
            System.out.println("운행중이 아닙니다.");
            return false;
        }
        return true;
    }

    void changeSpeed(int changeSpeed) {
        if(!checkOil()) return;
        this.speed += changeSpeed;
        if (this.speed > 100) {
            System.out.println("시속 100km/h 를 넘으면 과속입니다. 100km/h로 설정합니다.");
            this.speed = 100;
        } else if (this.speed < 0) {
            System.out.println("시속 0km/h 입니다. 운행중 이지만 정지 상태입니다.");
            this.speed = 0;
        }
        System.out.println("현재속도 " + this.speed + "km/h");


    }
    void setOil(){
        //속도, 함수 호출시간, 운행중 여부에 따라 소비된 기름 양을 계산해주는 함수
        if(this.state=="운행"){
            long timeNow = System.currentTimeMillis();
            int usedGas = (int) (this.timePerGas*(timeNow-this.gasCheckTime)/1000/60);
            this.gas = this.gas > usedGas ? this.gas - usedGas : 0;
            this.gasCheckTime = timeNow;
        }
    }

    void startCar(){
        //운행 시작
        if(!checkOil()) return;
        this.state = "운행";
        this.speed = 60;
        System.out.println("운행을 시작합니다. 기본 속도는 60 입니다.");
    }






}