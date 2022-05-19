import jdk.nashorn.internal.objects.annotations.Constructor;

public class Bus extends Car{
    final int maxPassenger = 30;  // 최대 승객수
    int currentPassenger = 0;  // 현재 승객수
    int price; // 요금

    public Bus(String carNumber, int price) {
        super(carNumber);
        this.car = "Bus";
        this.price = price;
        this.printCarInfo();
        System.out.println("생성 완료");
    }


    @Override
    void stopCar() {
        System.out.println("차고지 로 향합니다.");
        this.state = "차고지 행";
        this.speed = 0;
    }
    @Override
    void printCarInfo() {
        setOil();
        System.out.printf("차량 번호 : %s(%s), 상태: %s, 주유량: %d, 현재속도: %d, 현재 탑승객:%d, 최대 탑승객:%d, 기본요금: %d원\n", carNumber, car, state, gas, speed,currentPassenger,maxPassenger,price);
    }


    @Override
    void addPassenger(int addCount) {
        if(!checkOil()) return;
        if(!checkState()) return;
        if (maxPassenger < addCount + currentPassenger) {
            System.out.printf("최대 탑승객 %d명을 초과하여 %d명만 탑승했습니다.\n", maxPassenger, maxPassenger - currentPassenger);
            currentPassenger = maxPassenger;
        }else{
            System.out.printf("%d명이 탑승하였습니다.\n", addCount);
            currentPassenger += addCount;
        }
    }

    void removePassenger(int... count) {
        if (currentPassenger <= count[0]) {
            System.out.printf("현재 탑승객 %d명이 모두 내렸습니다.\n",currentPassenger);
            currentPassenger = 0;
        }else{
            System.out.printf("%d명이 하차하였습니다.\n", count[0]);
            currentPassenger -= count[0];
        }

    }

}