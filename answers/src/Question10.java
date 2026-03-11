import java.util.*;

class VideoData { String id; VideoData(String id){this.id=id;} }

class MultiLevelCache {
    LinkedHashMap<String, VideoData> L1 = new LinkedHashMap<>(16,0.75f,true);
    Map<String, VideoData> L2 = new HashMap<>();
    Map<String, VideoData> L3 = new HashMap<>();

    public MultiLevelCache() { L3.put("video_123", new VideoData("video_123")); }

    public VideoData getVideo(String videoId) {
        if(L1.containsKey(videoId)) return L1.get(videoId);
        if(L2.containsKey(videoId)) { L1.put(videoId,L2.get(videoId)); return L2.get(videoId); }
        if(L3.containsKey(videoId)) { L2.put(videoId,L3.get(videoId)); return L3.get(videoId); }
        return null;
    }
}

public class Question10 {
    public static void main(String[] args) {
        MultiLevelCache cache = new MultiLevelCache();
        System.out.println(cache.getVideo("video_123").id);
        System.out.println(cache.getVideo("video_999"));
    }
}