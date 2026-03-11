import java.util.*;

class ParkingLot {
    private final int size;
    private final String[] spots;

    public ParkingLot(int size) {
        this.size = size;
        this.spots = new String[size];
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    public int parkVehicle(String plate) {
        int idx = hash(plate);
        int probes = 0;
        while(spots[idx] != null) { idx = (idx +1)%size; probes++; }
        spots[idx] = plate;
        System.out.println("Assigned spot #" + idx + " (" + probes + " probes)");
        return idx;
    }

    public void exitVehicle(String plate) {
        for(int i=0;i<size;i++) {
            if(plate.equals(spots[i])) { spots[i]=null; System.out.println("Spot #" + i + " freed"); return; }
        }
    }
}

public class Question8 {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(500);
        lot.parkVehicle("ABC-1234");
        lot.parkVehicle("ABC-1235");
        lot.exitVehicle("ABC-1234");
    }
}