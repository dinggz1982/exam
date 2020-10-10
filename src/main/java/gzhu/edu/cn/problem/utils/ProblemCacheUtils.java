package gzhu.edu.cn.problem.utils;

import gzhu.edu.cn.base.util.EhCacheUtil;
import gzhu.edu.cn.base.util.SpringContextHolder;
import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.problem.entity.ProblemProgrammingDeatil;
import gzhu.edu.cn.problem.entity.ProblemProgrammingSamples;
import gzhu.edu.cn.problem.entity.ProblemTag;
import gzhu.edu.cn.problem.service.impl.ProblemBaseInformationService;
import gzhu.edu.cn.problem.service.impl.ProblemProgrammingDeatilService;
import gzhu.edu.cn.problem.service.impl.ProblemProgrammingSamplesService;
import gzhu.edu.cn.problem.service.impl.ProblemTagService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.hibernate.cache.spi.support.CacheUtils;

import java.util.List;

/**
 * @program: exam
 * @description: 试题缓存-加快访问速度
 * @author: 丁国柱
 * @create: 2020-09-21 00:53
 */
public class ProblemCacheUtils {

    private static ProblemBaseInformationService problemBaseInformationService = SpringContextHolder.getBean(ProblemBaseInformationService.class);
    private static ProblemProgrammingDeatilService problemProgrammingDeatilService = SpringContextHolder.getBean(ProblemProgrammingDeatilService.class);
    private static ProblemProgrammingSamplesService problemProgrammingSamplesService = SpringContextHolder.getBean(ProblemProgrammingSamplesService.class);
    private static ProblemTagService problemTagService = SpringContextHolder.getBean(ProblemTagService.class);

    public static final String CACHE_PROBLEM = "problemCache";

    /**
     * 获取题目基本信息
     *
     * @return
     */
    public static ProblemBaseInformation getProblemBaseInformation(int problemId) throws Exception {
        String cacheName = "problemBaseInformation" + problemId;
        ProblemBaseInformation problemBaseInformation = null;
        Cache cache = CacheManager.getInstance().getCache(cacheName);
        if (cache == null) {
            problemBaseInformation = problemBaseInformationService.findById(problemId);
            //获取标签
            List<ProblemTag> problemTags = problemTagService.find(" problemId="+problemId);
            problemBaseInformation.setProblemTags(problemTags);
            Element element = new Element(cacheName, problemBaseInformation);
            CacheManager.getInstance().addCache(cacheName);
            cache = CacheManager.getInstance().getCache(cacheName);
            cache.put(element);
        } else {
            Element element = cache.get(cacheName);
            if (element == null) {
                problemBaseInformation = problemBaseInformationService.findById(problemId);
                //获取标签
                List<ProblemTag> problemTags = problemTagService.find(" problemId="+problemId);
                problemBaseInformation.setProblemTags(problemTags);
                element = new Element(cacheName, problemBaseInformation);
                //CacheManager.getInstance().addCache(cacheName);
                //cache = CacheManager.getInstance().getCache(cacheName);
                cache.put(element);
            } else {
                problemBaseInformation = (ProblemBaseInformation) element.getObjectValue();
            }
        }
        return problemBaseInformation;
    }

    /**
     * 获取程序题描述信息
     *
     * @return
     */
    public static ProblemProgrammingDeatil getProgrammingDeatil(int problemId) throws Exception {
        String cacheName = "programmingDeatil_" + problemId;

        ProblemProgrammingDeatil deatil = null;
        Cache cache = CacheManager.getInstance().getCache(cacheName);
        if (cache == null) {
            deatil = problemProgrammingDeatilService.getByProblemId(problemId);
            Element element = new Element(cacheName, deatil);
            CacheManager.getInstance().addCache(cacheName);
            cache = CacheManager.getInstance().getCache(cacheName);
            cache.put(element);
        } else {
            Element element = cache.get(cacheName);
            if (element == null) {
                deatil = problemProgrammingDeatilService.getByProblemId(problemId);
                element = new Element(cacheName, deatil);
                //CacheManager.getInstance().addCache(cacheName);
                //cache = CacheManager.getInstance().getCache(cacheName);
                cache.put(element);
            } else {
                deatil = (ProblemProgrammingDeatil) element.getObjectValue();
            }
        }
        return deatil;
    }

    /**
     * 获取程序题样例
     *
     * @param problemId
     * @return
     */
    public static List<ProblemProgrammingSamples> getProgrammingSamplesList(int problemId) throws Exception {
        String cacheName = "programmingSamplesList_" + problemId;

        List<ProblemProgrammingSamples> samplesList = null;
        Cache cache = CacheManager.getInstance().getCache(cacheName);
        if (cache == null) {
            samplesList = problemProgrammingSamplesService.getSamplesListByProblemId(problemId);
            Element element = new Element(cacheName, samplesList);
            CacheManager.getInstance().addCache(cacheName);
            cache = CacheManager.getInstance().getCache(cacheName);
            cache.put(element);
        } else {
            Element element = cache.get(cacheName);
            if (element == null) {
                samplesList = problemProgrammingSamplesService.getSamplesListByProblemId(problemId);
                element = new Element(cacheName, samplesList);
                //CacheManager.getInstance().addCache(cacheName);
                //cache = CacheManager.getInstance().getCache(cacheName);
                cache.put(element);
            } else {
                samplesList = (List<ProblemProgrammingSamples>) element.getObjectValue();
            }
        }
        return samplesList;
    }

}