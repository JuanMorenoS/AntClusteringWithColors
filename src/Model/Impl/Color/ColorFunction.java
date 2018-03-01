package Model.Impl.Color;

import Model.Abstraction.Function;
import Model.Abstraction.Particle;
import Model.Impl.CoreFactoryCreator;

public class ColorFunction implements Function<int[]> {

	@Override
	public int probPick(int x, int y, Particle<int[]> p) { // Eq 5.14
		double k1 = CoreFactoryCreator.getFactory().getInstance().getK1();
		return (int) Math.pow(
				k1 / (k1 + itemAverageSimilarity(x, y, CoreFactoryCreator.getFactory().getInstance().getRadio(), p)), 2)
				* 100;
	}

	@Override
	public int probDeposit(int x, int y, Particle<int[]> p) { // Eq 5.15
		double fx = itemAverageSimilarity(y, x, CoreFactoryCreator.getFactory().getInstance().getRadio(), p);
		double k2 = CoreFactoryCreator.getFactory().getInstance().getK2();
		System.out.println(fx);
		return (int) (fx < k2 ? 2.0 * fx : 1.0) * 100;
	}

	@Override
	public double itemPerceivedFraction(int x, int y, int r, Particle<int[]> p) { // XXX verificar si esto es
																					// necesario .
		int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
		for (int i = x - r; i < x + r; i++) {
			for (int j = y - r; j < y + r; j++)
				if (0 <= i && i < maxX && 0 <= j && j < maxY) {

				}
		}

		return 0;
	}

	@Override
	public double itemAverageSimilarity(int x, int y, int r, Particle<int[]> p) { // Eq 5.13 o mirar Eq 5.16 en caso de
																					// // querer meter velocidad //FIXME mirar que esta pasando poque esta dando 0
		int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
		double alpha = CoreFactoryCreator.getFactory().getInstance().getAlpha();
		double sum = 0.0;
		for (int i = x - r; i <= x + r; i++) { //FIXME verificar estos rangos
			for (int j = y - r; j <= y + r; j++) {
				System.out.println("0<="+i+"<"+maxX+"- 0<="+j+"<"+maxY);
				if (0 <= i && i < maxX && 0 <= j && j < maxY) {
					ColorParticle tempParticle = (ColorParticle) CoreFactoryCreator.getFactory().getInstance()
							.getParticles()[i][j];
					if (tempParticle != null) {
						sum += 1 - (p.euclideanDistance(tempParticle) / alpha);
						System.out.println(sum);
					}
				}
			}
		}
		double f = sum / (r * r);
		 //System.out.println(f);
		return f > 0 ? f : 0;
	}

}
