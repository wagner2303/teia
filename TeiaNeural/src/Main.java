import java.util.ArrayList;
import java.util.Calendar;

import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataPair;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.quick.QuickPropagation;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.nnet.learning.BackPropagation;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		// 5 entradas = 5 roteadores
		//		MultiLayerPerceptron mlPerceptron = new MultiLayerPerceptron(new int[]{5, 4, 1});
		////		Perceptron perceptron = new Perceptron(5, 1);
		//
		//		DataSet dataSet = new DataSet(5, 1);
		//		// 15 salas
		//		for (int i = 0; i < 15; i++) {
		//			ArrayList<Double> input = new ArrayList<>();
		//			// 5 roteadores
		//			for (int j = 0; j < 5; j++) {
		//				
		//				input.add(Math.random() * 100);
		//			}
		//			ArrayList<Double> output= new ArrayList<>();
		//			output.add((double)i); // Número da sala =P
		//			DataSetRow row = new DataSetRow(input, output);
		//			System.out.println(row.toString());
		//			dataSet.addRow(row);
		//		}
		////		perceptron.randomizeWeights();
		//		mlPerceptron.randomizeWeights();
		//		System.out.println("Oi");
		//		Calendar inicio = Calendar.getInstance();
		//		mlPerceptron.learn(dataSet);
		////		perceptron.learn(dataSet);
		//		System.out.println((inicio.getTimeInMillis() - Calendar.getInstance().getTimeInMillis())/ 1000);
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(5));
		network.addLayer(new BasicLayer((5 * 2)+1));
		network.addLayer(new BasicLayer(1));

		// finalize structure
		network.getStructure().finalizeStructure();
		network.reset();

		MLDataSet dataSet = new BasicMLDataSet();
		for (int i = 0; i < 15; i++) {
			double[] input = new double[5];
			double[] ideal = new double[]{i};
			// 5 roteadores
			for (int j = 0; j < input.length; j++) {
				input[j] = Math.random() * 100;
			}
			MLDataPair dataPair = new BasicMLDataPair(new BasicMLData(input), new BasicMLData(ideal));
			dataSet.add(dataPair);
		}

		MLTrain train = new QuickPropagation(network, dataSet, 1);
//		MLTrain train = new Backpropagation(network, dataSet, 0.1, 0.3);
		do {
			train.iteration();
			System.out.println("Iteração " + train.getIteration() + " " +
					train.getError());
		} while ((train.getError() > 1) && (train.getIteration() < 2000000000));
		System.out.println(network.calculateError(dataSet));
	}

}
