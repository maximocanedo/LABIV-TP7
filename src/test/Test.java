package test;

import data.*;
import entity.*;
import logic.*;
import max.*;

public class Test {

	public static void out(Object e) {
		System.out.println(e);
	}
	public static InsuranceData DATA = new InsuranceData();
	public static InsuranceLogic LOGIC = new InsuranceLogic();
	public static void main(String[] args) {
		out("OK");
		LogicResponse<Insurance> lri = LOGIC.getAll();
		for(Insurance i : lri.listReturned) {
			out(i.toString());
		}
	}

}
