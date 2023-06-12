package com.commex.console;

public class ProgressBarReporter
{
	private int total;
	
	private String preCursorText = "Progress: ";
	
	private int percentageComplete = 0;
	
	private String[] bars = {"[          ]","[=         ]","[==        ]","[===       ]","[====      ]","[=====     ]","[======    ]","[=======   ]","[========  ]","[========= ]","[==========]"};
	
	
	public void setPreCursorText(String text)
	{
		this.preCursorText = text.trim().concat(" ");
	}
	
	public void setMeasureAgainst(int measureAgainst)
	{
		this.total = measureAgainst;
	}
	
	public void start()
	{
		this.showProgress();
	}
	
	private void showProgress()
	{
		String bar = bars[Math.floorDiv(this.percentageComplete, 10)];
		
		System.out.print(new StringBuilder().append(this.preCursorText).append(bar).append(" ").append(this.percentageComplete).append("%\r").toString());
	}
	
	public void reportProgress(int progress)
	{
		int percentageComplete = Math.floorDiv(progress *100, this.total);
		
		if(percentageComplete != this.percentageComplete)
		{
			this.percentageComplete = percentageComplete;
			this.showProgress();
		}
	}
	public void close()
	{
		System.out.print("\n");
	}
	
}
