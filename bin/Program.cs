Console.WriteLine("Введите число");
string numBin = Console.ReadLine();
double result = 0;
int point = numBin.IndexOf('.');
double j = point-1;
for (int i = 0; i < numBin.Length; i++)
{
    if (!numBin[i].Equals('.'))
    {
        Console.WriteLine($"{numBin[i]}   ---   {j}   ---   {(numBin[i] - '0') * Math.Pow(2, j)}");
        result += (numBin[i] - '0')*Math.Pow(2, j);
        j--;
    }
}
Console.WriteLine(result);