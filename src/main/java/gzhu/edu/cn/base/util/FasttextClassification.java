package gzhu.edu.cn.base.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.mayabot.mynlp.fasttext.FastText;
import com.mayabot.mynlp.fasttext.FloatStringPair;

public class FasttextClassification {
	
	/**
	 * 预测
	 * @param model
	 * @param content
	 * @return
	 */
	public static String predict(FastText model,String content){
		
		List<FloatStringPair> predict = model.predict(Arrays.asList(createFenci(content).split(" ")), 5);
		
		FloatStringPair floatStringPair = predict.get(0);
		
		System.out.println(floatStringPair.second);
		
		return floatStringPair.second;
		
	}

	public static String createFenci(String content){
		List<Term> termList = NLPTokenizer.segment(content);
		String str = "";
		for (Iterator iterator = termList.iterator(); iterator.hasNext();) {
			Term term = (Term) iterator.next();
			String word =term.word;
			if(str.indexOf(word)==-1){
				str = str+term.word+" ";
			}
		}
		return str.trim();
	}
}
