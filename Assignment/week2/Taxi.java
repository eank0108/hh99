public class Taxi extends Car{
    int basicDistance = 3; // 기본 거리
    int pricePerDistance = 1000; // 거리당 요금 1000원
    boolean hasPassenger = false;//승객이 타고 있는지
    int price = 0; //승객이 하차시 계산할 요금
    public Taxi(String carNumber) {
        super(carNumber);
        this.car = "Taxi";
        System.out.println("생성 완료");
    }


    @Override
    void stopCar() {
        System.out.println("운행을 정지합니다.");
        this.state = "일반";
        this.speed = 0;
    }

    @Override
    void printCarInfo() {
        setOil();
        System.out.printf("차량 번호 : %s(%s), 상태: %s, 주유량: %d, 현재속도: %d, 기본거리: %dkm, 거리당 요금 %d원 \n", carNumber, car, state, gas, speed,basicDistance,pricePerDistance);
    }

    @Override
    void addPassenger(int distance) {
        if(hasPassenger){
            System.out.println("승객이 이미 타고 있습니다.");
            return;
        }
        if(!checkOil()) return;
        if(!checkState()) return;
        int price;
        if (distance < basicDistance) {
            price = basicDistance*pricePerDistance;
        }else{
            price = distance * pricePerDistance;
        }
        state = "운행";
        hasPassenger = true;
        this.price = price;
        System.out.printf("승객이 탑승하였습니다. 이동할 거리 %d km, 총 요금은 %d원 입니다.\n", distance, price);

    }
    void removePassenger(int... count){
        if (!hasPassenger) {
            System.out.println("타고 있는 승객이 없습니다.");
            return;
        }
        this.hasPassenger = false;
        System.out.printf("승객이 하차하였습니다. 총 요금은 %d원 입니다.\n", price);
        this.price = 0;

    }
}
