package com.yolo.wz.redisdemo.sortset;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

/**
 * 音乐排行榜
 */
public class MusicRankingListDemo {
    private Jedis jedis = new Jedis("127.0.0.1");

    /**
     * 新音乐加入排行榜
     */
    public void addMusic(long songId) {
        jedis.zadd("music_rank_list", 0, String.valueOf(songId));
    }

    /**
     * 增加歌曲的分数
     */
    public void incrementSongScore(long songId, double score) {
        jedis.zincrby("music_rank_list", score, String.valueOf(songId));
    }

    /**
     * 获取歌曲在排行榜周的排名
     */
    public long getSongRank(long songId) {
        return jedis.zrevrank("music_rank_list", String.valueOf(songId));
    }

    /**
     * 展示排名前三的音乐
     * @return
     */
    public Set<Tuple> getMusicRanking(){
        return jedis.zrevrangeWithScores("music_rank_list",0,2);
    }

    public static void main(String[] args) {
        MusicRankingListDemo demo = new MusicRankingListDemo();
        for (int i = 0; i < 20; i++) {
            demo.addMusic(i+1);
        }

        demo.incrementSongScore(8,8.2);
        demo.incrementSongScore(5,16.9);
        demo.incrementSongScore(9,13.2);
        demo.incrementSongScore(1,3.7);

        System.out.println("查看8的歌曲在多少名："+demo.getSongRank(8));

        System.out.println("查看排名前三的歌曲是哪些："+demo.getMusicRanking().toString());
    }
}
