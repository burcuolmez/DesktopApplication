package src;
public class Queue implements IQueue {
	private int front;
	private int rear;
	private Object[] contents;

	public Queue(int cap) {
		front = rear = 0;
		contents = new Object[cap];
	}

	@Override
	public void enqueue(Object item) throws QueueFull {
		if (isFull())
			throw new QueueFull();

		contents[rear] = item;
		rear = (rear + 1) % contents.length;

	}

	@Override
	public Object peek() throws QueueEmpty {
		if (isEmpty())
			throw new QueueEmpty();

		return contents[front];
	}

	@Override
	public Object dequeue() throws QueueEmpty {
		if (isEmpty())
			throw new QueueEmpty();

		Object retVal = contents[front];
		contents[front] = null;
		front = (front + 1) % contents.length;
		
		return retVal;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return rear == front;
	}
	
	public boolean isFull()
	{
		return front == (rear + 1) % contents.length;
	}

	@Override
	public String toString()
	{
		
		
		String retString="";
		
		for(int i=front; i!= rear; i= (i+1) % contents.length)
		{
			retString += contents[i] + " ";
		}
		
		
		
		return retString;
	}
	
	public Object[] values() {
		return contents;
	}

	public int getFront() {
		return front;
	}

	public void setFront(int front) {
		this.front = front;
	}

	public int getRear() {
		return rear;
	}

	public void setRear(int rear) {
		this.rear = rear;
	}

	public Object[] getContents() {
		return contents;
	}

	public void setContents(Object[] contents) {
		this.contents = contents;
	}
	
	
	
	
}











